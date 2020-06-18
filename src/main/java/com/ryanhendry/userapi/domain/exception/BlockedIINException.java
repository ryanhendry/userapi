package com.ryanhendry.userapi.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_ACCEPTABLE, reason = "User PAN contains blocked IIN")
public class BlockedIINException extends RuntimeException {
}
