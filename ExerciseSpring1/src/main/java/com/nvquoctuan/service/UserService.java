package com.nvquoctuan.service;

import com.nvquoctuan.entity.UserEntity;
import java.util.List;

public interface UserService {

  List<UserEntity> findByUserNameOrFirstName(String keyword, Integer pageNumber);
  List<UserEntity> findByUserNameOrFullName(String keyword);
  UserEntity createUser(UserEntity userEntity);
}
