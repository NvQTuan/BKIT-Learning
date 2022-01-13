package com.nvquoctuan.service;

import com.nvquoctuan.dto.UserDto;
import com.nvquoctuan.constant.PageConstant;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

  @Autowired
  private UserRepository userRepository;

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
  public UserEntity createUser(UserDto userDto) {
    return userRepository.save(UserEntity.builder()
        .userName(userDto.getUserName())
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .birthDay(userDto.getBirthDay())
        .build());
  }

  private Integer getPageNumber(Integer pageNumber) {
    return pageNumber <= 1 ? 0 : pageNumber - 1;
  }
}
