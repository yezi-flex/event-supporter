package io.yezi.broadcast.domains.user.domain;

import org.springframework.test.util.ReflectionTestUtils;

import io.yezi.broadcast.domains.user.application.dto.SignUpRequest;
import io.yezi.broadcast.domains.user.domain.model.User;

public class UserFixtureGenerator {
	public static Long ID = 1L;
	public static String USERNAME = "사용자 식별자";
	public static String PASSWORD = "password";

	public static User 사용자_도메인_객체_생성() {
		return User.of(USERNAME, PASSWORD);
	}

	public static User 사용자_도메인_객체_생성(String username, String password) {
		return User.of(username, password);
	}

	public static SignUpRequest 회원_가입_요청_객체_생성() {
		return new SignUpRequest(USERNAME, PASSWORD);
	}

	public static User 식별자가_존재하는_사용자_도메인_객체_생성() {
		return 식별자가_존재하는_사용자_도메인_객체_생성(ID);
	}

	public static User 식별자가_존재하는_사용자_도메인_객체_생성(Long id) {
		User user = 사용자_도메인_객체_생성();
		ReflectionTestUtils.setField(user, "id", id);
		return user;
	}

}
