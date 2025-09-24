package io.yezi.broadcast.common.error.response;

import static lombok.AccessLevel.*;

import java.time.LocalDateTime;

import io.yezi.broadcast.common.error.code.WebErrorCode;
import io.yezi.broadcast.common.error.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor(access = PROTECTED)
public class BusinessErrorResponse {

	private String method;
	private String path;
	private String code;
	private String message;
	private LocalDateTime timestamp;

	public static BusinessErrorResponse handleGlobalError(
		HttpServletRequest request,
		WebErrorCode errorCode
	) {
		return new BusinessErrorResponse(
			request.getMethod(),
			request.getRequestURI(),
			errorCode.name(),
			errorCode.getMessage(),
			LocalDateTime.now()
		);
	}

	public static BusinessErrorResponse handleApplicationError(
		HttpServletRequest request,
		BusinessException exception
	) {
		return new BusinessErrorResponse(
			request.getMethod(),
			request.getRequestURI(),
			exception.getCode(),
			exception.getMessage(),
			LocalDateTime.now()
		);
	}

}
