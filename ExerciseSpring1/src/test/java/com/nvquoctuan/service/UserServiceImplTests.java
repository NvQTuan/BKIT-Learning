package com.nvquoctuan.service;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.Date;
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
  void getUser_whenFindByUserNameOrFullName_thenReturnUser() {
    when(userRepository.findByUserNameContains(anyString())).thenReturn(anyList());
    userServiceImpl.getUserByFullName(anyString());
    verify(userRepository, times(1)).findByUserNameContains(anyString());
  }

  @Test
  void saveUser_whenSaveUser_thenReturnUser() {
    final UserEntity userEntity = UserEntity.builder()
        .userName("userName")
        .firstName("userName")
        .lastName("lastName" )
        .birthDay(new Date())
        .insertedDate(new Date())
        .build();

    userServiceImpl.createUser(userEntity);
    verify(userRepository, times(1)).save(userEntity);
  }
}
