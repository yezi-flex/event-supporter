package io.yezi.broadcast.domains.user.application.dto;

import io.yezi.broadcast.domains.user.domain.model.User;

public record SignUpRequest(
	String username,
	String password
) {
	public User toEntity() {
		return User.of(username, password);
	}
}
