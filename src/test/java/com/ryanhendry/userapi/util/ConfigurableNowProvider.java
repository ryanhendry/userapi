package com.ryanhendry.userapi.util;

import com.ryanhendry.userapi.infrastructure.system.NowProvider;
import java.time.LocalDate;

public class ConfigurableNowProvider implements NowProvider {

  private final LocalDate now;

  public ConfigurableNowProvider(LocalDate now) {
    this.now = now;
  }

  @Override
  public LocalDate now() {
    return now;
  }
}
