package io.yezi.broadcast.domains.streamer.application;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import io.yezi.broadcast.domains.streamer.application.policy.StreamerValidationRegistry;
import io.yezi.broadcast.domains.streamer.domain.StreamerFixtureGenerator;
import io.yezi.broadcast.domains.streamer.domain.model.Streamer;
import io.yezi.broadcast.domains.streamer.domain.repository.StreamerRepo;

@DisplayName("Service:Streamer")
@ExtendWith(MockitoExtension.class)
class StreamerServiceTest {

	@Mock
	private StreamerRepo streamerRepo;

	@Mock
	private StreamerValidationRegistry streamerValidationRegistry;

	@InjectMocks
	private StreamerService streamerService;

	@DisplayName("스트리머 생성")
	@Nested
	class createTest {
		@Test
		@DisplayName("중복된 정보가 아닌 경우 스트리머를 생성 한다.")
		void create() {
			// Given
			var createUserRequest = StreamerFixtureGenerator.스트리머_등록_요청_객체_생성();
			stubSaveReturnsEntityWithId();

			// When
			Long id = streamerService.create(createUserRequest);

			// Then
			assertThat(id).isEqualTo(StreamerFixtureGenerator.ID);
			ArgumentCaptor<Streamer> userCaptor = ArgumentCaptor.forClass(Streamer.class);
			then(streamerRepo).should().save(userCaptor.capture());

			assertStreamerSavedWith(userCaptor.getValue(), createUserRequest);
		}

		private void stubSaveReturnsEntityWithId() {
			BDDMockito.given(streamerRepo.save(any(Streamer.class)))
				.willAnswer(invocation -> StreamerFixtureGenerator.식별자가_존재하는_스트리머_도메인_객체_생성());
		}

		private void assertStreamerSavedWith(Streamer actual, CreateStreamerRequest expected) {
			assertThat(actual)
				.extracting(Streamer::getChannelId, Streamer::getLoginId, Streamer::getNickname, Streamer::getStreamUrl)
				.containsExactly(expected.channelId(), expected.loginId(), expected.nickname(), expected.streamUrl());
		}

		@Test
		@DisplayName("[예외] 스트리머 정보가 중복인 경우")
		void throwException_WhenDuplicatedStreamerChannelId() {
			// Given
			var request = StreamerFixtureGenerator.스트리머_등록_요청_객체_생성();

			willThrow(new IllegalArgumentException())
				.given(streamerValidationRegistry)
				.validate(request);

			// When & Then
			assertThatExceptionOfType(IllegalArgumentException.class)
				.isThrownBy(() -> streamerService.create(request));
			then(streamerRepo).shouldHaveNoInteractions();
		}
	}

}
