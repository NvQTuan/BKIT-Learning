package com.nvquoctuan.service;

import com.nvquoctuan.constant.PageConstant;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;

  @Autowired
  public UserServiceImpl(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  @Override
  public List<UserEntity> findByUserNameOrFirstName(String keyword, Integer pageNumber) {
    Pageable pageable = PageRequest.of(getPageNumber(pageNumber), PageConstant.PAGE_SIZE);
    return userRepository.findByUserNameOrFirstName(keyword, pageable);
  }

  @Override
  public List<UserEntity> findByUserNameOrFullName(String search) {
    if (search == null) {
      return new ArrayList<>();
    }
    Set<UserEntity> responseUserEntity = new HashSet<>();
    Map<Integer, Integer> indexCharInString = new HashMap<>();
    AtomicInteger i = new AtomicInteger();
    AtomicInteger index = new AtomicInteger();
    List<String> keywords = Stream.of(search.split("\\s")).collect(Collectors.toList());
    keywords.forEach(keyword -> {
      indexCharInString.put(i.intValue(), index.intValue());
      i.getAndIncrement();
      index.addAndGet(keyword.length() + 1);
    });
    indexCharInString.forEach((i1, value) -> {
      final int indexEndLastName = value + keywords.get(i1).length();
      final String lastNameKeyword = search
          .substring(0, Math.min(indexEndLastName, search.length()));
      final int indexStartFirstName = value + keywords.get(i1).length() + 1;
      final String firstNameKeyword = search.length() > indexStartFirstName ? search
          .substring(indexStartFirstName) : "";
      responseUserEntity.addAll(userRepository.findByFullName(lastNameKeyword, firstNameKeyword));
    });
    responseUserEntity.addAll(userRepository.findByUserNameContains(search));
    return new ArrayList<>(responseUserEntity);
  }

  @Override
  public UserEntity createUser(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  private Integer getPageNumber(Integer pageNumber) {
    return pageNumber <= 1 ? 0 : pageNumber - 1;
  }
}
