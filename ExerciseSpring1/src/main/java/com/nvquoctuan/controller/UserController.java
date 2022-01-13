package com.nvquoctuan.controller;

import com.nvquoctuan.dto.UserDto;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.service.UserServiceImpl;
import java.util.List;
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

  @GetMapping("/username-or-firstname")
  public ResponseEntity<?> getByUserNameOrFirstName(@RequestParam(name = "q",
      defaultValue = "") String keyword, @RequestParam(defaultValue = "1") Integer page) {
    final List<UserEntity> userEntity = userService.findByUserNameOrFirstName(keyword, page);
    if (userEntity.isEmpty()) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok().body(userEntity);
  }

  @GetMapping("/username-or-fullname")
  public ResponseEntity<?> getByUserNameOrFullName(@RequestParam(name = "q",
      defaultValue = "") String keyword) {
    return userService.findByUserNameOrFullName(keyword)
        .map(user -> ResponseEntity.ok().body(user)).orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto) {
    final UserEntity userEntity = UserEntity.builder()
        .userName(userDto.getUserName())
        .firstName(userDto.getFirstName())
        .lastName(userDto.getLastName())
        .birthDay(userDto.getBirthDay())
        .build();
    return new ResponseEntity<>(userService.createUser(userEntity), HttpStatus.CREATED);
  }
}
