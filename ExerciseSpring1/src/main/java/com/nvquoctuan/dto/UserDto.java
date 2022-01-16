package com.nvquoctuan.dto;

import com.nvquoctuan.constant.ValidateConstant;
import java.util.Date;
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

  @Size(min = ValidateConstant.MIN_SIZE_USERNAME, max = ValidateConstant.MAX_SIZE_USERNAME,
      message = "The userName must be between {min} and {max} characters")
  private String userName;

  @Size(max = ValidateConstant.MAX_SIZE_CHARACTER,
      message = "The firstName must be less than {max} characters")
  private String firstName;

  @Size(max = ValidateConstant.MAX_SIZE_CHARACTER,
      message = "The lastName must be less than {max} characters")
  private String lastName;

  private Date birthDay;
}
