package io.yezi.broadcast.domains.user.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.yezi.broadcast.config.base.DataJpaTestBase;
import io.yezi.broadcast.domains.user.domain.UserFixtureGenerator;
import io.yezi.broadcast.domains.user.domain.model.User;
import io.yezi.broadcast.domains.user.infrastructure.UserRepoAdapter;

@Import(UserRepoAdapter.class)
public class UserRepoTestHelper extends DataJpaTestBase {
	@Autowired
	protected UserRepo userRepo;

	protected User savedStreamer() {
		User user = UserFixtureGenerator.사용자_도메인_객체_생성();
		return flushAndClear(() -> userRepo.save(user));
	}

	protected User fetchedStreamer() {
		User user = savedStreamer();
		return userRepo.findById(user.getId());
	}
}
