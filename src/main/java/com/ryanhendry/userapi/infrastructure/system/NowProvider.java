package com.ryanhendry.userapi.infrastructure.system;

import java.time.LocalDate;

public interface NowProvider {
  LocalDate now();
}
