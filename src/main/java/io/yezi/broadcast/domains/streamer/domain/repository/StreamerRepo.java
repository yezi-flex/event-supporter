package io.yezi.broadcast.domains.streamer.domain.repository;

import io.yezi.broadcast.domains.streamer.domain.model.Streamer;

public interface StreamerRepo {
	Streamer save(Streamer streamer);

	Streamer findById(Long id);

	boolean existByLoginId(String loginId);

	boolean existByStreamUrl(String streamUrl);

	boolean existByNickname(String nickname);

	boolean existByChannelId(Long channelId);
}
