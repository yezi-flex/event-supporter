package io.yezi.broadcast.domains.user.infrastructure;

import org.springframework.stereotype.Repository;

import io.yezi.broadcast.domains.user.domain.model.User;
import io.yezi.broadcast.domains.user.domain.repository.UserRepo;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class UserRepoAdapter implements UserRepo {
	private final UserJpaRepo userJpaRepo;

	@Override
	public User save(User user) {
		return userJpaRepo.save(user);
	}

	@Override
	public User findById(Long id) {
		return userJpaRepo.findById(id).orElseThrow();
	}

	@Override
	public boolean existByUsername(String username) {
		return userJpaRepo.existsByUsername(username);
	}

	@Override
	public User findByUsername(String username) {
		return userJpaRepo.findByUsername(username).orElseThrow();
	}
}
