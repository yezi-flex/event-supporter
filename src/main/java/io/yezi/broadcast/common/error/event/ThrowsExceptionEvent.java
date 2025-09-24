package io.yezi.broadcast.common.error.event;

import io.yezi.broadcast.common.error.response.BusinessErrorResponse;
import lombok.Getter;

@Getter
public final class ThrowsExceptionEvent extends StackTraceExtractor {

	private final Exception exception;
	private final BusinessErrorResponse response;

	private ThrowsExceptionEvent(
		Exception exception,
		BusinessErrorResponse response
	) {
		super(exception);
		this.exception = exception;
		this.response = response;
	}

	public static ThrowsExceptionEvent of(
		Exception exception,
		BusinessErrorResponse response
	) {
		return new ThrowsExceptionEvent(exception, response);
	}

}
