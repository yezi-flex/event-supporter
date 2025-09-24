package io.yezi.broadcast.common.error.exception;

import org.springframework.http.HttpStatusCode;

import io.yezi.broadcast.common.error.code.ErrorCode;
import lombok.Getter;

@Getter
public class BusinessException extends RuntimeException {

	private final ErrorCode errorCode;

	public BusinessException(ErrorCode errorCode) {
		super(errorCode.getMessage());
		this.errorCode = errorCode;
	}

	public BusinessException(ErrorCode errorCode, String errorMessage) {
		super(errorMessage);
		this.errorCode = errorCode;
	}

	public String getCode() {
		return errorCode.getCode();
	}

	public HttpStatusCode getStatus() {
		return errorCode.getStatus();
	}
}
