package io.yezi.broadcast.domains.streamer.application.policy;

import org.springframework.stereotype.Component;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import io.yezi.broadcast.domains.streamer.domain.repository.StreamerRepo;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StreamerLoginIdValidationPolicy implements StreamerValidationPolicy {
	private final StreamerRepo streamerRepo;

	@Override
	public void validate(CreateStreamerRequest request) {
		if (streamerRepo.existByLoginId(request.loginId())) {
			throw new IllegalArgumentException("LoginId " + request.loginId() + " already exists");
		}
	}
}
