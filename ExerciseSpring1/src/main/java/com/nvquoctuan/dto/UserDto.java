package com.nvquoctuan.dto;

import com.nvquoctuan.constant.ValidateConstant;
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

  @Size(min = ValidateConstant.MIN_SIZE_USERNAME, max = ValidateConstant.MAX_SIZE_USERNAME)
  private String userName;

  @Size(max = ValidateConstant.MAX_SIZE_CHARACTER)
  private String firstName;

  @Size(max = ValidateConstant.MAX_SIZE_CHARACTER)
  private String lastName;
  private Date birthDay;
}
