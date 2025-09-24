package io.yezi.broadcast.domains.streamer.domain;

import org.springframework.test.util.ReflectionTestUtils;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import io.yezi.broadcast.domains.streamer.domain.model.Streamer;

public class StreamerFixtureGenerator {
	public static Long ID = 1L;
	public static Long CHANNEL_ID = 1L;
	public static String LOGIN_ID = "loginId";
	public static String NICKNAME = "닉네임";
	public static String STREAM_URL = "https://www.strem.com/channels/1/live";

	public static Streamer 스크리머_도메인_객체_생성() {
		return Streamer.of(CHANNEL_ID, LOGIN_ID, NICKNAME, STREAM_URL);
	}

	public static Streamer 스크리머_도메인_객체_생성(Long channelId, String loginId, String nickName, String streamUrl) {
		return Streamer.of(channelId, loginId, nickName, streamUrl);
	}

	public static CreateStreamerRequest 스트리머_등록_요청_객체_생성() {
		return new CreateStreamerRequest(CHANNEL_ID, LOGIN_ID, NICKNAME, STREAM_URL);
	}

	public static Streamer 식별자가_존재하는_스트리머_도메인_객체_생성() {
		return 식별자가_존재하는_스트리머_도메인_객체_생성(ID);
	}

	public static Streamer 식별자가_존재하는_스트리머_도메인_객체_생성(Long id) {
		Streamer streamer = 스크리머_도메인_객체_생성();
		ReflectionTestUtils.setField(streamer, "id", id);
		return streamer;
	}

}
