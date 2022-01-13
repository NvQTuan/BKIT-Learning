package com.nvquoctuan.service;

import com.nvquoctuan.entity.UserEntity;
import java.util.List;
import java.util.Optional;

public interface UserService {

  List<UserEntity> findByUserNameOrFirstName(String keyword, Integer pageNumber);
  Optional<UserEntity> findByUserNameOrFullName(String keyword);
  UserEntity createUser(UserEntity userEntity);
}
