package com.ryanhendry.userapi.infrastructure.system;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class SystemNowProvider implements NowProvider {

  @Override
  public LocalDate now() {
    return LocalDate.now();
  }
}
