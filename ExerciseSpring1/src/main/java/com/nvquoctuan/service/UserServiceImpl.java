package com.nvquoctuan.service;

import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public Page<UserEntity> findByUserNameOrFirstName(String search, Pageable pageable) {
    return null;
  }

  @Override
  public List<UserEntity> findByUserNameOrFirstNameAndLastName(String search) {
    return null;
  }
}
