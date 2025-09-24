package io.yezi.broadcast.domains.streamer.application.policy;

import org.springframework.stereotype.Component;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import io.yezi.broadcast.domains.streamer.domain.repository.StreamerRepo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StreamerChannelIdValidationPolicy implements StreamerValidationPolicy {
	private final StreamerRepo streamerRepo;

	@Override
	public void validate(CreateStreamerRequest request) {
		if (streamerRepo.existByChannelId(request.channelId())) {
			throw new IllegalArgumentException("ChannelId " + request.channelId() + " already exists");
		}
	}
}
