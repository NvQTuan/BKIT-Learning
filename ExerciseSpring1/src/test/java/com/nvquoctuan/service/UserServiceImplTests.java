package com.nvquoctuan.service;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userServiceImpl;

  // TODO fix error can't execute below test
//  @Test
//  void getUser_whenFindByUserNameOrFirstName_thenReturnUser() {
//    Pageable pageable = PageRequest.of(0, 1);
//    userServiceImpl.getUserByFirstName(anyString(), anyInt(), anyInt());
//    verify(userRepository, times(1)).findByFirstName(anyString(),
//        eq(pageable));
//  }

  @Test
  void getUser_whenFindByFullName_thenReturnUser() {
    when(userRepository.findByFirstNameStartsWith(anyString())).thenReturn(anyList());
    userServiceImpl.getUserByFullName(anyString());
    verify(userRepository, times(1)).findByFirstNameStartsWith(anyString());
    verify(userRepository, times(1)).findByLastNameStartsWith(anyString());
  }

  @Test
  void saveUser_whenSaveUser_thenReturnUser() {
    final UserEntity userEntity = buildUserEntity();
    userServiceImpl.createUser(userEntity);
    verify(userRepository, times(1)).save(userEntity);
  }

  @Test
  void checkUser_whenExistsUserName_thenReturnFalse() {
    boolean actual = userServiceImpl.existsByUserName(anyString());
    verify(userRepository, times(1)).existsByUserName(anyString());
    Assertions.assertFalse(actual);
  }

  private UserEntity buildUserEntity() {
    return UserEntity.builder()
        .userName("userName")
        .firstName("userName")
        .lastName("lastName" )
        .birthDay(new Date())
        .insertedDate(new Date())
        .build();
  }
}
