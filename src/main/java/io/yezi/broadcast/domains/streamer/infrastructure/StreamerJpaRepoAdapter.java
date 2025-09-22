package io.yezi.broadcast.domains.streamer.infrastructure;

import org.springframework.stereotype.Repository;

import io.yezi.broadcast.domains.streamer.domain.model.Streamer;
import io.yezi.broadcast.domains.streamer.domain.repository.StreamerRepo;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class StreamerJpaRepoAdapter implements StreamerRepo {
	private final StreamerJpaRepo streamerJpaRepo;

	@Override
	public Streamer save(Streamer streamer) {
		return streamerJpaRepo.save(streamer);
	}

	@Override
	public Streamer findById(Long id) {
		return streamerJpaRepo.findById(id).orElseThrow();
	}
}
