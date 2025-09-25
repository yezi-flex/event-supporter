package io.yezi.broadcast.domains.user.domain.repository;

import io.yezi.broadcast.domains.user.domain.model.User;

public interface UserRepo {
	User save(User user);

	User findByUsername(String username);

	User findById(Long id);

	boolean existByUsername(String username);
}
