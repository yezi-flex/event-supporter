package io.yezi.broadcast.domains.user.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import io.yezi.broadcast.domains.user.domain.model.User;

public interface UserJpaRepo extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
}
