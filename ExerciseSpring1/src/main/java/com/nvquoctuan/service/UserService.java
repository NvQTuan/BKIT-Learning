package com.nvquoctuan.service;

import com.nvquoctuan.entity.UserEntity;
import java.util.List;

public interface UserService {

  List<UserEntity> getUserByFirstName(String firstName, int pageNumber, int size);
  List<UserEntity> getUserByFullName(String keyword);
  UserEntity createUser(UserEntity userEntity);
  boolean existsByUserName(String userName);
}
