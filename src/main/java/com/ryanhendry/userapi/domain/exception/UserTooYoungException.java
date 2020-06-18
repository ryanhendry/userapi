package com.ryanhendry.userapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "User is too young")
public class UserTooYoungException extends RuntimeException {
}
