package com.ryanhendry.userapi.infrastructure.rest;

import com.ryanhendry.userapi.domain.entity.dao.UserDao;
import com.ryanhendry.userapi.domain.entity.dto.UserDto;
import com.ryanhendry.userapi.domain.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public UserDao register(@Validated @RequestBody UserDto user) {
    return userService.saveUser(user);
  }
}
