package com.nvquoctuan.service;

import com.nvquoctuan.constant.PageConstant;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public List<UserEntity> findByUserNameOrFirstName(String search, Integer pageNumber) {
    Pageable pageable = PageRequest.of(pageNumber, PageConstant.PAGE_SIZE);
    return userRepository.findByUserNameOrFirstName(search, pageable);
  }

  @Override
  public List<UserEntity> findByUserNameOrFirstNameAndLastName(String search) {
    return userRepository.findByUserNameOrFullName(search);
  }
}
