package com.nvquoctuan.controller;

import com.nvquoctuan.constant.PageConstant;
import com.nvquoctuan.dto.CustomErrorResponseDto;
import com.nvquoctuan.dto.UserDto;
import com.nvquoctuan.dto.UserResponseDto;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.service.UserServiceImpl;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Slf4j
public class UserController {

  private final UserServiceImpl userService;

  @GetMapping("/getUser")
  public List<UserResponseDto> getByUserNameOrFirstName(@RequestParam(name = "firstName",
      defaultValue = "") String firstName,
      @RequestParam(defaultValue = PageConstant.PAGE_NUMBER) Integer page,
      @RequestParam(required = false, defaultValue = PageConstant.PAGE_SIZE) Integer size) {
    final List<UserEntity> userEntity = userService.getUserByFirstName(firstName, page, size);
    return userEntity.stream().map(user -> buildUserResponseDto(user.getUserName()))
        .collect(Collectors.toList());
  }

  @GetMapping("/getUserByFullName")
  public List<UserResponseDto> getByUserNameOrFullName(@RequestParam(name = "keyword",
      defaultValue = "") String keyword) {
    final List<UserEntity> userEntity = userService.getUserByFullName(keyword);
    return userEntity.stream().map(user -> buildUserResponseDto(user.getUserName()))
        .collect(Collectors.toList());
  }

  @PostMapping
  public ResponseEntity<?> createUser(@Valid @RequestBody UserDto userDto) {
    final UserEntity userEntity = UserEntity.builder()
        .userName(userDto.getUserName())
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .birthDay(userDto.getBirthDay())
        .build();
    if (userService.existsByUserName(userDto.getUserName())) {
      return new ResponseEntity<>(CustomErrorResponseDto.builder()
          .status(HttpStatus.BAD_REQUEST).message("UserName is exists").build(),
          HttpStatus.BAD_REQUEST);
    }
    final UserEntity responseUserEntity = userService.createUser(userEntity);
    return new ResponseEntity<>(buildUserResponseDto(responseUserEntity.getUserName()), HttpStatus.CREATED);
  }

  private UserResponseDto buildUserResponseDto(String userName) {
    return UserResponseDto.builder()
        .userName(userName)
        .build();
  }
}
