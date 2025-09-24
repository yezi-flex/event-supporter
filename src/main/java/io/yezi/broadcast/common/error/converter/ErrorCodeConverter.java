package io.yezi.broadcast.common.error.converter;

import static org.apache.logging.log4j.util.Strings.*;

import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ErrorCodeConverter {
	private static final String EXCEPTION_SUFFIX = "Exception";
	private static final Pattern CAMEL_TO_SNAKE_PATTERN = Pattern.compile("([a-z])([A-Z])");

	public static String toErrorCode(Exception exception) {
		String className = extractExceptionClassName(exception);
		String camelExceptionName = removeExceptionSuffix(className);
		String snakeCase = toSnakeCase(camelExceptionName);

		return toUpperCase(snakeCase);
	}

	private static String extractExceptionClassName(Exception exception) {
		return exception.getClass().getSimpleName();
	}

	private static String removeExceptionSuffix(String className) {
		return className.replace(EXCEPTION_SUFFIX, EMPTY);
	}

	private static String toSnakeCase(String value) {
		String replacement = "$1_$2";
		return CAMEL_TO_SNAKE_PATTERN.matcher(value)
			.replaceAll(replacement);
	}

	private static String toUpperCase(String value) {
		return value.toUpperCase();
	}
}
