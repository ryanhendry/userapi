package com.ryanhendry.userapi.domain.entity.assembler;

import com.ryanhendry.userapi.domain.entity.dao.UserDao;
import com.ryanhendry.userapi.domain.entity.dto.UserDto;

public class UserDaoAssembler {

  public static UserDao assemble(UserDto user) {
    return new UserDao(null, user.getUsername(), user.getPassword(), user.getDob(), user.getPaymentCardNumber());
  }

}
