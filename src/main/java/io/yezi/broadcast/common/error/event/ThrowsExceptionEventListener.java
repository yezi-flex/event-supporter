package io.yezi.broadcast.common.error.event;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import io.yezi.broadcast.common.error.response.BusinessErrorResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ThrowsExceptionEventListener {

	@Async
	@EventListener
	public void onThrowsExceptionLogging(ThrowsExceptionEvent throwsExceptionEvent) {
		Exception exception = throwsExceptionEvent.getException();
		BusinessErrorResponse response = throwsExceptionEvent.getResponse();
		log.error("""
				\s
				    - METHOD: {}
				    - PATH: {}
				    - CODE: {}
				    - MESSAGE: {}
				    - EXCEPTION: {}, {}
				    - TRACE : {}
				\s""",
			response.getMethod(),
			response.getPath(),
			response.getCode(),
			response.getMessage(),
			exception.getClass().getSimpleName(),
			exception.getMessage(),
			throwsExceptionEvent.formatStackTrace()
		);
	}

}
