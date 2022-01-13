package com.nvquoctuan.controller;

import com.nvquoctuan.dto.UserDto;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.service.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UserController {

  @Autowired
  private UserServiceImpl userService;

  @GetMapping("username-or-firstname")
  public ResponseEntity<List<UserEntity>> getByUserNameOrFirstName(@RequestParam(name = "q",
      defaultValue = "") String keyword, @RequestParam(defaultValue = "0") Integer page) {

    final List<UserEntity> responseListUser = userService.findByUserNameOrFirstName(keyword, page);
    return new ResponseEntity<>(responseListUser, HttpStatus.OK);
  }

  @GetMapping("username-or-fullname")
  public ResponseEntity<List<UserEntity>> getByUserNameOrFullName(@RequestParam(name = "q",
      defaultValue = "") String keyword) {
    final List<UserEntity> responseListUser = userService.findByUserNameOrFullName(keyword);
    return new ResponseEntity<>(responseListUser, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto) {
    return new ResponseEntity<>(userService.createUser(userDto), HttpStatus.CREATED);
  }
}
