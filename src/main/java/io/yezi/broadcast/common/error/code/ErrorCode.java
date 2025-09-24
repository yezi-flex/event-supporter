package io.yezi.broadcast.common.error.code;

import org.springframework.http.HttpStatus;

import io.yezi.broadcast.common.error.exception.BusinessException;

public interface ErrorCode {
	HttpStatus getStatus();

	String getCode();

	String getMessage();

	default BusinessException create(Object... args) {
		String errorMessage = getMessage().formatted(args);
		return new BusinessException(this, errorMessage);
	}
}
