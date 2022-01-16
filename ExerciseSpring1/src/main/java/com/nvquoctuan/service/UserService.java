package com.nvquoctuan.service;

import com.nvquoctuan.dto.UserResponseDto;
import com.nvquoctuan.entity.UserEntity;
import java.util.List;

public interface UserService {

  List<UserEntity> getUserByFirstName(String firstName, int pageNumber, int size);
  List<UserEntity> getUserByFullName(String keyword);
  UserEntity createUser(UserEntity userEntity);
}
