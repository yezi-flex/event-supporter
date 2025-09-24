package io.yezi.broadcast.common.error.code;

import static org.springframework.http.HttpStatus.*;

import java.util.Arrays;

import org.springframework.http.HttpStatus;

import io.yezi.broadcast.common.error.converter.ErrorCodeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum WebErrorCode implements ErrorCode {
	MISSING_PATH_VARIABLE(BAD_REQUEST, "경로에 필요한 변수가 없습니다. 요청 URI를 확인해 주세요."),
	MISSING_SERVLET_REQUEST_PARAMETER(BAD_REQUEST, "필수 요청 값이 누락되었습니다. 요청 파라미터를 확인해 주세요."),
	METHOD_ARGUMENT_TYPE_MISMATCH(BAD_REQUEST, "요청 값의 자료형이 올바르지 않습니다. 자료형을 다시 확인해 주세요."),
	METHOD_ARGUMENT_NOT_VALID(BAD_REQUEST, "유효하지 않은 요청 값이 포함되었습니다. 필수 항목이 빠졌거나 자료형이 잘못되었습니다. 입력 데이터를 다시 확인해 주세요."),
	HTTP_MESSAGE_NOT_READABLE(BAD_REQUEST, "요청 본문을 읽을 수 없습니다. 자료형이나 데이터를 확인해 주세요."),
	HTTP_MEDIA_TYPE_NOT_ACCEPTABLE(BAD_REQUEST, "요청하신 응답 형식을 처리할 수 없습니다. Accept 헤더 값을 확인해 주세요."),
	NO_RESOURCE_FOUND(NOT_FOUND, "해당 경로에 대한 리소스를 찾을 수 없습니다. URL이나 API 경로를 확인해 주세요."),
	HTTP_REQUEST_METHOD_NOT_SUPPORTED(METHOD_NOT_ALLOWED, "지원하지 않는 HTTP 메소드입니다. 요청 메소드를 확인해 주세요."),
	HTTP_MEDIA_TYPE_NOT_SUPPORTED(UNSUPPORTED_MEDIA_TYPE, "지원하지 않는 Content-Type입니다. Content-Type 헤더를 확인해 주세요."),
	MISSING_ROLE_HEADER(UNAUTHORIZED, "요청에서 권한 정보를 찾을 수 없습니다. 요청 헤더에 권한 정보를 포함해 주세요."),
	INVALID_ROLE(UNAUTHORIZED, "권한 정보가 올바르지 않습니다. 요청 헤더의 권한 정보를 확인해 주세요."),
	INVALID_USER_ID(UNAUTHORIZED, "유효하지 않은 사용자 정보 입니다. 요청 헤더에 사용자 식별자 정보를 확인해주세요"),
	ACCESS_DENIED(FORBIDDEN, "접근 권한이 없습니다. 관리자에게 문의하거나 권한을 확인한 후 다시 요청해 주세요."),
	UNEXPECTED_ERROR(INTERNAL_SERVER_ERROR, "알 수 없는 오류가 발생했습니다. 잠시 후 다시 시도해 주세요. 문제가 계속되면 관리자에게 문의해 주세요."),
	;

	public final HttpStatus status;
	public final String message;

	public static WebErrorCode from(Exception exception) {
		String errorCode = ErrorCodeConverter.toErrorCode(exception);

		return Arrays.stream(WebErrorCode.values())
			.filter(code -> code.isMatched(errorCode))
			.findFirst()
			.orElse(WebErrorCode.UNEXPECTED_ERROR);
	}

	private boolean isMatched(String errorCode) {
		return name().equals(errorCode);
	}

	@Override
	public String getCode() {
		return this.name();
	}
}
