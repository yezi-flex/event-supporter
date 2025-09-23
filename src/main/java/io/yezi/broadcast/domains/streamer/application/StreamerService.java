package io.yezi.broadcast.domains.streamer.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import io.yezi.broadcast.domains.streamer.application.policy.StreamerValidationRegistry;
import io.yezi.broadcast.domains.streamer.domain.model.Streamer;
import io.yezi.broadcast.domains.streamer.domain.repository.StreamerRepo;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StreamerService {

	private final StreamerRepo streamerRepo;
	private final StreamerValidationRegistry streamerValidationRegistry;

	@Transactional
	public Long create(CreateStreamerRequest request) {
		streamerValidationRegistry.validate(request);
		Streamer streamer = request.toEntity();

		return streamerRepo.save(streamer).getId();
	}
}
