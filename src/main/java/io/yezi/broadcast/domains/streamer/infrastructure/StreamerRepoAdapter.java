package io.yezi.broadcast.domains.streamer.infrastructure;

import org.springframework.stereotype.Repository;

import io.yezi.broadcast.domains.streamer.domain.model.Streamer;
import io.yezi.broadcast.domains.streamer.domain.repository.StreamerRepo;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StreamerRepoAdapter implements StreamerRepo {
	private final StreamerJpaRepo streamerJpaRepo;

	@Override
	public Streamer save(Streamer streamer) {
		return streamerJpaRepo.save(streamer);
	}

	@Override
	public Streamer findById(Long id) {
		return streamerJpaRepo.findById(id).orElseThrow();
	}

	@Override
	public boolean existByLoginId(String loginId) {
		return streamerJpaRepo.existsByLoginId(loginId);
	}

	@Override
	public boolean existByStreamUrl(String streamUrl) {
		return streamerJpaRepo.existsByStreamUrl(streamUrl);
	}

	@Override
	public boolean existByNickname(String nickname) {
		return streamerJpaRepo.existsByNickname(nickname);
	}

	@Override
	public boolean existByChannelId(Long channelId) {
		return streamerJpaRepo.existsByChannelId(channelId);
	}
}
