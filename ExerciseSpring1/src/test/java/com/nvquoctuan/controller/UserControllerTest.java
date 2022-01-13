package com.nvquoctuan.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.nvquoctuan.constant.PageConstant;
import com.nvquoctuan.entity.UserEntity;
import com.nvquoctuan.service.UserServiceImpl;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserServiceImpl userService;

  private List<UserEntity> userEntityList;

  @Test
  void getUser_whenFindByUserNameOrFirstNameWithPage_thenReturnUser() throws Exception {
    when(userService.findByUserNameOrFirstName(anyString(), anyInt())).thenReturn(userEntityList);
    mockMvc.perform(MockMvcRequestBuilders.get("/api/user/username-or-firstname?q=n&page=1")
        .accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(anyString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value(anyString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value(anyString()))
        .andExpect(MockMvcResultMatchers.jsonPath("$.birthDay").value(any(Date.class)));

    Assertions.assertEquals(userEntityList.size(), PageConstant.PAGE_SIZE);
  }
}
