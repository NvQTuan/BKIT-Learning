package com.nvquoctuan.service;

import com.nvquoctuan.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserService {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userService;


}
