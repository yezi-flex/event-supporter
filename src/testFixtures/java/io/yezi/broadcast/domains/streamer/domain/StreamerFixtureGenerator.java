package io.yezi.broadcast.domains.streamer.domain;

import io.yezi.broadcast.domains.streamer.domain.model.Streamer;

public class StreamerFixtureGenerator {
	public static Long CHANNEL_ID = 582119L;
	public static String LOGIN_ID = "loveoao46";
	public static String NICKNAME = "[🐷]이푸린";
	public static String STREAM_URL = "https://www.ttinglive.com/channels/582119/live";

	public static Streamer createStreamer() {
		return Streamer.of(CHANNEL_ID, LOGIN_ID, NICKNAME, STREAM_URL);
	}

	public static Streamer createStreamer(Long channelId, String loginId, String nickName, String streamUrl) {
		return Streamer.of(channelId, loginId, nickName, streamUrl);
	}
}
