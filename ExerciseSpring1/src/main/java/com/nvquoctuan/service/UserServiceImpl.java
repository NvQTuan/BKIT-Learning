package com.nvquoctuan.service;

import com.nvquoctuan.constant.PageConstant;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.List;
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
    return userRepository.findByUserNameOrFullName(search);
  }

  @Override
  public UserEntity createUser(UserEntity userEntity) {
    return userRepository.save(userEntity);
  }

  private Integer getPageNumber(Integer pageNumber) {
    return pageNumber <= 1 ? 0 : pageNumber - 1;
  }
}
