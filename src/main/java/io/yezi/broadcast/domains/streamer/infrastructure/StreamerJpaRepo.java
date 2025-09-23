package io.yezi.broadcast.domains.streamer.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import io.yezi.broadcast.domains.streamer.domain.model.Streamer;

public interface StreamerJpaRepo extends JpaRepository<Streamer, Long> {
	boolean existsByLoginId(String loginId);

	boolean existsByStreamUrl(String streamUrl);

	boolean existsByNickname(String nickname);

	boolean existsByChannelId(Long channelId);
}
