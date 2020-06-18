package com.ryanhendry.userapi.domain.entity.dto;

import java.time.LocalDate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserDto {
  @NotBlank(message = "username must not be blank")
  @Pattern(regexp = "^[A-Za-z0-9]+$", message = "username must be alphanumeric with no spaces")
  private final String username;
  @Size(min = 4, message = "password must be 4 characters minimum")
  @Pattern(regexp = "(.*\\d.*)", message = "Password must contain at least one number")
  @Pattern(regexp = "(.*[A-Z].*)", message = "Password must contain at least one upper case character")
  private final String password;
  @NotNull(message = "date must not be null and be in ISO-8601 format")
  private final LocalDate dob;
  @Size(min = 15, max = 19, message = "paymentCardNumber must be between 15 and 19 characters")
  private final String paymentCardNumber;

  public UserDto(String username, String password, LocalDate dob, String paymentCardNumber) {
    this.username = username;
    this.password = password;
    this.dob = dob;
    this.paymentCardNumber = paymentCardNumber;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public LocalDate getDob() {
    return dob;
  }

  public String getPaymentCardNumber() {
    return paymentCardNumber;
  }
}
