package io.yezi.broadcast.domains.streamer.application.dto;

import io.yezi.broadcast.domains.streamer.domain.model.Streamer;

public record CreateStreamerRequest(
	Long channelId,
	String loginId,
	String nickname,
	String streamUrl
) {

	public Streamer toEntity() {
		return Streamer.of(channelId, loginId, nickname, streamUrl);
	}
}
