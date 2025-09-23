package io.yezi.broadcast.domains.streamer.domain.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import io.yezi.broadcast.config.DataJpaTestBase;
import io.yezi.broadcast.domains.streamer.domain.StreamerFixtureGenerator;
import io.yezi.broadcast.domains.streamer.domain.model.Streamer;
import io.yezi.broadcast.domains.streamer.infrastructure.StreamerJpaRepoAdapter;

@Import(StreamerJpaRepoAdapter.class)
public class StreamerRepoTestHelper extends DataJpaTestBase {
	@Autowired
	protected StreamerRepo streamerRepo;

	protected Streamer savedStreamer() {
		Streamer streamer = StreamerFixtureGenerator.스크리머_도메인_객체_생성();
		return flushAndClear(() -> streamerRepo.save(streamer));
	}

	protected Streamer fetchedStreamer() {
		Streamer streamer = savedStreamer();
		return streamerRepo.findById(streamer.getId());
	}
}
