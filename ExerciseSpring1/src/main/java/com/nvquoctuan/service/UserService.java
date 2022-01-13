package com.nvquoctuan.service;

import com.nvquoctuan.dto.UserDto;
import com.nvquoctuan.entity.UserEntity;
import java.util.List;

public interface UserService {

  public List<UserEntity> findByUserNameOrFirstName(String keyword, Integer pageNumber);
  public List<UserEntity> findByUserNameOrFullName(String keyword);
  public UserEntity createUser(UserDto userDto);
}
