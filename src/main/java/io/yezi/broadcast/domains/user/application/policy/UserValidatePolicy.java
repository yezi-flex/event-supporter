package io.yezi.broadcast.domains.user.application.policy;

import io.yezi.broadcast.domains.user.application.dto.SignUpRequest;

public interface UserValidatePolicy {
	void validate(SignUpRequest signUpRequest);
}
