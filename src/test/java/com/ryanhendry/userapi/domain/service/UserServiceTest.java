package com.ryanhendry.userapi.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.ryanhendry.userapi.domain.entity.assembler.UserDaoAssembler;
import com.ryanhendry.userapi.domain.entity.dto.UserDto;
import com.ryanhendry.userapi.domain.exception.BlockedIINException;
import com.ryanhendry.userapi.domain.exception.UserExistsException;
import com.ryanhendry.userapi.domain.exception.UserTooYoungException;
import com.ryanhendry.userapi.infrastructure.db.UserRepository;
import com.ryanhendry.userapi.util.ConfigurableNowProvider;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class UserServiceTest {

  private UserService userService;
  private UserRepository mockRepository;

  @BeforeEach
  void setUp() {
    List<String> blockedIINs = new ArrayList<>();
    blockedIINs.add("123456");
    blockedIINs.add("987654");
    mockRepository = mock(UserRepository.class);
    userService = new UserService(
        mockRepository,
        blockedIINs,
        new ConfigurableNowProvider(LocalDate.of(2020,5,16)));
  }

  @Test
  void testSaveUser() {
    UserDto user = new UserDto(
        "AzureDiamond",
        "Hunter2",
        LocalDate.of(2002,5,16),
        "7463812635382615");
    userService.saveUser(user);
    verify(mockRepository, times(1)).save(any());
  }

  @ParameterizedTest
  @ValueSource(strings = {"2020-05-16","2002-05-17","2006-02-02"})
  void testSaveFailsIfUserUnder18(String date) {
   UserDto user = new UserDto(
       "AzureDiamond",
       "Hunter2",
       LocalDate.parse(date),
       "7463812635382615");
   assertThrows(UserTooYoungException.class, () -> userService.saveUser(user));
  }

  @Test
  void testSaveFailsIfUserAlreadyExists() {
    UserDto user = new UserDto(
        "AzureDiamond",
        "Hunter2",
        LocalDate.of(2002,5,16),
        "7463812635382615");
    when(mockRepository.findByUsername(any())).thenReturn(Optional.of(UserDaoAssembler.assemble(user)));
    assertThrows(UserExistsException.class, () -> userService.saveUser(user));
  }

  @ParameterizedTest
  @ValueSource(strings = {"1234562635382615","9876542635382615"})
  void testSaveFailsIfBlockedIIN(String paymentCardNumber) {
    UserDto user = new UserDto(
        "AzureDiamond",
        "Hunter2",
        LocalDate.of(2002,5,16),
        paymentCardNumber
        );
    assertThrows(BlockedIINException.class, () -> userService.saveUser(user));
  }
}