package io.yezi.broadcast.domains.user.application;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.yezi.broadcast.domains.user.application.dto.SignUpRequest;
import io.yezi.broadcast.domains.user.application.policy.UserValidatePolicy;
import io.yezi.broadcast.domains.user.domain.model.User;
import io.yezi.broadcast.domains.user.domain.repository.UserRepo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {
	private final UserValidatePolicy userValidatePolicy;
	private final PasswordEncoder passwordEncoder;
	private final UserRepo userRepo;

	@Transactional
	public Long create(SignUpRequest request) {
		userValidatePolicy.validate(request);
		String encodedPassword = passwordEncoder.encode(request.password());
		User user = request.toEntity(encodedPassword);

		return userRepo.save(user).getId();
	}
}
