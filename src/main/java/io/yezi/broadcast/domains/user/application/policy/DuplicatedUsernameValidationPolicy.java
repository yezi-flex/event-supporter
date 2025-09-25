package io.yezi.broadcast.domains.user.application.policy;

import org.springframework.stereotype.Component;

import io.yezi.broadcast.domains.user.application.dto.SignUpRequest;
import io.yezi.broadcast.domains.user.domain.repository.UserRepo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DuplicatedUsernameValidationPolicy implements UserValidatePolicy {
	private final UserRepo userRepo;

	@Override
	public void validate(SignUpRequest signUpRequest) {
		if (userRepo.existByUsername(signUpRequest.username())) {
			throw new IllegalArgumentException("Username is already in use");
		}
	}
}
