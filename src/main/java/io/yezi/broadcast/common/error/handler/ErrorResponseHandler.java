package io.yezi.broadcast.common.error.handler;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import io.yezi.broadcast.common.error.event.ThrowsExceptionEvent;
import io.yezi.broadcast.common.error.response.BusinessErrorResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ErrorResponseHandler {
	private final ApplicationEventPublisher eventPublisher;

	protected ResponseEntity<BusinessErrorResponse> handleResponse(
		Exception exception,
		HttpStatusCode statusCode,
		BusinessErrorResponse errorResponse
	) {
		eventPublisher.publishEvent(ThrowsExceptionEvent.of(exception, errorResponse));
		return ResponseEntity.status(statusCode)
			.body(errorResponse);
	}
}
