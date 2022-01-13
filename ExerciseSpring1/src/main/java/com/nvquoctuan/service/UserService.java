package com.nvquoctuan.service;

import com.nvquoctuan.entity.UserEntity;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

  public Page<UserEntity> findByUserNameOrFirstName(String search, Pageable pageable);

  public List<UserEntity> findByUserNameOrFirstNameAndLastName(String search);
}
