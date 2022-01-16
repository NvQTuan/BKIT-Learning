package com.nvquoctuan.service;

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
  public List<UserEntity> getUserByFirstName(String firstName, int pageNumber, int size) {
    final Pageable pageable = PageRequest.of(getPageNumber(pageNumber), size);
    return userRepository.findByFirstNameStartsWith(firstName, pageable);
  }

  @Override
  public List<UserEntity> getUserByFullName(String keywordSearch) {
    if (keywordSearch == null) {
      return new ArrayList<>();
    }
    Set<UserEntity> responseUserEntity = new HashSet<>();
    Map<Integer, Integer> indexCharInString = new HashMap<>();
    AtomicInteger i = new AtomicInteger();
    AtomicInteger index = new AtomicInteger();
    List<String> keywords = Stream.of(keywordSearch.split("\\s")).collect(Collectors.toList());
    keywords.forEach(keyword -> {
      indexCharInString.put(i.intValue(), index.intValue());
      i.getAndIncrement();
      index.addAndGet(keyword.length() + 1);
    });
    indexCharInString.forEach((i1, value) -> {
      final int indexEndLastName = value + keywords.get(i1).length();
      final String lastNameKeyword = keywordSearch
          .substring(0, Math.min(indexEndLastName, keywordSearch.length()));
      final int indexStartFirstName = value + keywords.get(i1).length() + 1;
      final String firstNameKeyword = keywordSearch.length() > indexStartFirstName ? keywordSearch
          .substring(indexStartFirstName) : "";
      System.out.println(lastNameKeyword + " - " + firstNameKeyword);
      responseUserEntity.addAll(userRepository
          .findByLastNameStartsWithAndFirstNameContains(lastNameKeyword, firstNameKeyword));
    });
    responseUserEntity.addAll(userRepository.findByFirstNameStartsWith(keywordSearch));
    responseUserEntity.addAll(userRepository.findByLastNameStartsWith(keywordSearch));
    return new ArrayList<>(responseUserEntity);
  }

  @Override
  public UserEntity createUser(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  @Override
  public boolean existsByUserName(String userName) { return userRepository.existsByUserName(userName); }
  private Integer getPageNumber(int pageNumber) {
    return pageNumber <= 1 ? 0 : pageNumber - 1;
  }
}
