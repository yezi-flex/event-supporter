package io.yezi.broadcast.domains.streamer.domain.repository;

import io.yezi.broadcast.domains.streamer.domain.model.Streamer;

public interface StreamerRepo {
	Streamer save(Streamer streamer);

	Streamer findById(Long id);
}
