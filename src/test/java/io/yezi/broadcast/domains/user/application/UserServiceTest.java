package io.yezi.broadcast.domains.user.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.yezi.broadcast.config.base.ServiceTestBase;
import io.yezi.broadcast.domains.user.application.dto.SignUpRequest;
import io.yezi.broadcast.domains.user.application.policy.UserValidatePolicy;
import io.yezi.broadcast.domains.user.domain.UserFixtureGenerator;
import io.yezi.broadcast.domains.user.domain.model.User;
import io.yezi.broadcast.domains.user.domain.repository.UserRepo;

@DisplayName("Service:User")
class UserServiceTest extends ServiceTestBase {

	@Mock
	private UserRepo userRepo;

	@Mock
	private UserValidatePolicy userValidatePolicy;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserService userService;

	@Nested
	@DisplayName("회원 생성")
	class createTest {

		@Test
		@DisplayName("중복된 정보가 없는 경우 회원을 생성한다.")
		void create() {
			// Given
			var given = UserFixtureGenerator.회원_가입_요청_객체_생성();
			stubSaveReturnsEntityWithId();

			// When
			Long actual = userService.create(given);

			// Then
			assertThat(actual).isEqualTo(UserFixtureGenerator.ID);
			ArgumentCaptor<User> userCaptor = ArgumentCaptor.forClass(User.class);
			then(userValidatePolicy).should().validate(given);
			then(passwordEncoder).should().encode(given.password());
			then(userRepo).should().save(userCaptor.capture());

			assertUserSavedWith(userCaptor.getValue(), given);
		}

		private void stubSaveReturnsEntityWithId() {
			BDDMockito.given(userRepo.save(any(User.class)))
				.willAnswer(invocation -> UserFixtureGenerator.식별자가_존재하는_사용자_도메인_객체_생성());
			given(passwordEncoder.encode(anyString())).willReturn("encodedPassword");
		}

		private void assertUserSavedWith(User actual, SignUpRequest expected) {
			assertThat(actual.getUsername()).isEqualTo(expected.username());
		}

		@Test
		@DisplayName("[예외]사용자 아이디가 중복된 경우")
		void throwException_WhenDuplicatedUsername() {
			// Given
			var given = UserFixtureGenerator.회원_가입_요청_객체_생성();

			// When
			willThrow(new IllegalArgumentException())
				.given(userValidatePolicy)
				.validate(given);

			// Then
			assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> userService.create(given));
			then(userRepo).shouldHaveNoInteractions();
		}
	}
}
