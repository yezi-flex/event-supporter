package io.yezi.broadcast.domains.streamer.domain.repository;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataIntegrityViolationException;

import io.yezi.broadcast.domains.streamer.domain.StreamerFixtureGenerator;
import io.yezi.broadcast.domains.streamer.domain.model.Streamer;

@DisplayName("Repository:Streamer")
class StreamerRepoTest extends StreamerRepoTestHelper {
	@Test
	@DisplayName("스트리머 정보를 삭제한다.")
	void delete() {
		// Given
		Streamer given = fetchedStreamer();

		// When
		flushAndClear(given::delete);

		// Then
		assertThat(given.isDeleted()).isTrue();
	}

	@Nested
	@DisplayName("스트리머 정보 조회")
	class findStreamerById {
		@Test
		@DisplayName("스트리머 정보를 조회한다.")
		void findById() {
			// Given
			Streamer given = savedStreamer();

			// When
			Streamer actual = assertDoesNotThrow(() -> streamerRepo.findById(given.getId()));

			// Then
			assertThat(actual.getId()).isEqualTo(given.getId());
		}

		@Test
		@DisplayName("[예외]존재하지 않는 스트리머 정보를 조회하는 경우")
		void throwException_WhenRetrieveNotExists() {
			// When & Then
			assertThatExceptionOfType(NoSuchElementException.class)
				.isThrownBy(() -> streamerRepo.findById(1L));
		}
	}

	@Nested
	@DisplayName("스트리머 정보 저장")
	class StreamerSaveTest {
		@Test
		@DisplayName("스트리머 정보를 저장한다.")
		void save() {
			// Given
			Streamer given = StreamerFixtureGenerator.스크리머_도메인_객체_생성();

			// When
			Streamer actual = streamerRepo.save(given);

			// Then
			assertAll(
				() -> assertThat(actual.getId()).isNotNull(),
				() -> assertThat(actual.getCreatedAt()).isNotNull(),
				() -> assertThat(actual.getUpdatedAt()).isNull()
			);
		}

		@Test
		@DisplayName("[예외]스트리머 채널 아이디가 중복인 경우")
		void throwException_WhenDuplicatedStreamerChannelId() {
			// Given
			Streamer given = savedStreamer();
			Streamer streamer = StreamerFixtureGenerator.스크리머_도메인_객체_생성(
				given.getChannelId(),
				"loginId",
				"nickname",
				"streamUrl"
			);

			// When & Then
			assertThatExceptionOfType(DataIntegrityViolationException.class)
				.isThrownBy(() -> flushAndClear(() -> streamerRepo.save(streamer)));
		}

		@Test
		@DisplayName("[예외]스트리머 채널 아이디가 중복인 경우")
		void throwException_WhenDuplicatedStreamerLoginId() {
			// Given
			Streamer given = savedStreamer();
			Streamer streamer = StreamerFixtureGenerator.스크리머_도메인_객체_생성(
				1L,
				given.getLoginId(),
				"nickname",
				"streamUrl"
			);

			// When & Then
			assertThatExceptionOfType(DataIntegrityViolationException.class)
				.isThrownBy(() -> flushAndClear(() -> streamerRepo.save(streamer)));
		}

		@Test
		@DisplayName("[예외]스트리머 스트리머 URL이 중복인 경우")
		void throwException_WhenDuplicatedStreamerStreamUrl() {
			// Given
			Streamer given = savedStreamer();
			Streamer streamer = StreamerFixtureGenerator.스크리머_도메인_객체_생성(
				1L,
				"loginId",
				"nickname",
				given.getStreamUrl()
			);

			// When & Then
			assertThatExceptionOfType(DataIntegrityViolationException.class)
				.isThrownBy(() -> flushAndClear(() -> streamerRepo.save(streamer)));
		}
	}
}
