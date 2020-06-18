package com.ryanhendry.userapi.domain.service;

import com.ryanhendry.userapi.domain.entity.assembler.UserDaoAssembler;
import com.ryanhendry.userapi.domain.entity.dao.UserDao;
import com.ryanhendry.userapi.domain.entity.dto.UserDto;
import com.ryanhendry.userapi.domain.exception.BlockedIINException;
import com.ryanhendry.userapi.domain.exception.UserExistsException;
import com.ryanhendry.userapi.domain.exception.UserTooYoungException;
import com.ryanhendry.userapi.infrastructure.db.UserRepository;
import com.ryanhendry.userapi.infrastructure.system.NowProvider;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final List<String> blockedIINs;
  private final NowProvider nowProvider;

  public UserService(UserRepository userRepository,
      @Value("#{'${app.blockedIINs}'.split(',')}") List<String> blockedIINs,
      NowProvider nowProvider) {
    this.userRepository = userRepository;
    this.blockedIINs = blockedIINs;
    this.nowProvider = nowProvider;
  }

  public UserDao saveUser(UserDto user) {
    validateDob(user);
    validateUsername(user);
    validateIIN(user);
    return userRepository.save(UserDaoAssembler.assemble(user));
  }

  private void validateDob(UserDto user) {
    LocalDate eighteenYears = nowProvider.now().minusYears(18);
    if (user.getDob().isAfter(eighteenYears) && !user.getDob().equals(eighteenYears)) {
      throw new UserTooYoungException();
    }
  }

  private void validateUsername(UserDto user) {
    if (userRepository.findByUsername(user.getUsername()).isPresent()) {
      throw new UserExistsException();
    }
  }

  private void validateIIN(UserDto user) {
    if (!blockedIINs.isEmpty()) {
      for (String IIN: blockedIINs) {
        if (user.getPaymentCardNumber().substring(0, 6).equals(IIN)) {
          throw new BlockedIINException();
        }
      }
    }
  }

}
