package com.nvquoctuan.service;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.nvquoctuan.constant.PageConstant;
import com.nvquoctuan.dto.UserDto;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.repository.UserRepository;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Pageable;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTests {

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private UserServiceImpl userServiceImpl;

  ArgumentCaptor<Pageable> captor = ArgumentCaptor.forClass(Pageable.class);

  @Test
  void getUser_whenFindByUserNameOrFirstName_thenReturnUser() {
    userServiceImpl.findByUserNameOrFirstName(anyString() , eq(PageConstant.PAGE_SIZE));
    verify(userRepository, times(1)).findByUserNameOrFirstName(anyString(),
        captor.capture());
  }

  @Test
  void getUser_whenFindByUserNameOrFullName_thenReturnUser() {
    userServiceImpl.findByUserNameOrFullName(anyString());
    verify(userRepository, times(1)).findByUserNameOrFullName(anyString());
  }

  @Test
  void saveUser_whenSaveUser_thenReturnUser() {
    final UserEntity userEntity = buildUserEntity();

    userServiceImpl.createUser(userEntity);
    verify(userRepository, times(1)).save(userEntity);
  }

  private UserEntity buildUserEntity() {
    return UserEntity.builder()
        .userName("userName")
        .firstName("userName")
        .lastName("lastName")
        .birthDay(new Date())
        .insertedDate(new Date())
        .build();
  }
}
