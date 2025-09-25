package io.yezi.broadcast.domains.user.domain.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import io.yezi.broadcast.domains.user.domain.model.User;

@DisplayName("Repository:User")
class UserRepoTest extends UserRepoTestHelper {

	@Test
	@DisplayName("사용자 정보를 저장한다.")
	void save() {
		// Given
		User given = User.of("username", "password");

		// When
		User actual = userRepo.save(given);

		// Then
		assertAll(
			() -> assertThat(actual.getId()).isNotNull(),
			() -> assertThat(actual.getCreatedAt()).isNotNull(),
			() -> assertThat(actual.getUpdatedAt()).isNull()
		);
	}

	@Nested
	@DisplayName("사용자 정보 조회")
	class findByUser {

		@Test
		@DisplayName("사용자 번호로 사용자 정보를 조회한다.")
		void findById() {
			// Given
			User given = savedStreamer();

			// When
			User actual = userRepo.findById(given.getId());

			// Then
			assertThat(actual.getId()).isEqualTo(given.getId());
		}

		@Test
		@DisplayName("사용자 아이디로 사용자 정보를 조회한다.")
		void findByUsername() {
			User given = savedStreamer();

			// When
			User actual = userRepo.findByUsername(given.getUsername());

			// Then
			assertThat(actual.getId()).isEqualTo(given.getId());
		}

		@Test
		@DisplayName("[예외] 존재하지 않는 사용자인 경우")
		void throwException_WhenNotExistUser() {
			// When & Then
			assertThatExceptionOfType(NoSuchElementException.class)
				.isThrownBy(() -> userRepo.findById(1L));
		}
	}
}
