package io.yezi.broadcast.domains.streamer.application.policy;

import org.springframework.stereotype.Component;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import io.yezi.broadcast.domains.streamer.domain.repository.StreamerRepo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StreamerStreamUrlValidationPolicy implements StreamerValidationPolicy {
	private final StreamerRepo streamerRepo;

	@Override
	public void validate(CreateStreamerRequest request) {
		if (streamerRepo.existByStreamUrl(request.toEntity().getStreamUrl())) {
			throw new IllegalArgumentException("StreamUrl " + request.toEntity().getStreamUrl() + " already exists");
		}
	}
}
