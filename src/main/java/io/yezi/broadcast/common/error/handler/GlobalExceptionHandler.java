package io.yezi.broadcast.common.error.handler;

import static io.yezi.broadcast.common.error.response.BusinessErrorResponse.*;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import io.yezi.broadcast.common.error.code.WebErrorCode;
import io.yezi.broadcast.common.error.exception.BusinessException;
import io.yezi.broadcast.common.error.response.BusinessErrorResponse;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler extends ErrorResponseHandler {

	public GlobalExceptionHandler(ApplicationEventPublisher eventPublisher) {
		super(eventPublisher);
	}

	@ExceptionHandler({
		MissingPathVariableException.class,
		MissingServletRequestParameterException.class,
		MethodArgumentTypeMismatchException.class,
		HttpMessageNotReadableException.class,
		HttpMediaTypeNotAcceptableException.class,
		NoResourceFoundException.class,
		HttpRequestMethodNotSupportedException.class,
		HttpMediaTypeNotSupportedException.class
	})
	public ResponseEntity<BusinessErrorResponse> invalidRequestException(
		Exception exception,
		HttpServletRequest request
	) {
		var errorCode = WebErrorCode.from(exception);
		var errorResponse = BusinessErrorResponse.handleGlobalError(request, errorCode);

		return handleResponse(exception, errorCode.status, errorResponse);
	}

	@ExceptionHandler({BusinessException.class})
	public ResponseEntity<BusinessErrorResponse> businessException(
		BusinessException exception,
		HttpServletRequest request
	) {
		BusinessErrorResponse errorResponse = handleApplicationError(request, exception);

		return handleResponse(exception, exception.getStatus(), errorResponse);
	}
}
