package com.nvquoctuan.dto;

import com.nvquoctuan.constant.ValidateEntityConstant;
import java.util.Date;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

  @Size(min = ValidateEntityConstant.MIN_SIZE_USERNAME, max = ValidateEntityConstant.MAX_SIZE_USERNAME)
  private String userName;

  @Max(value = ValidateEntityConstant.MAX_SIZE_CHARACTER)
  private String firstName;

  @Max(value = ValidateEntityConstant.MAX_SIZE_CHARACTER)
  private String lastName;
  private Date birthDay;
}
