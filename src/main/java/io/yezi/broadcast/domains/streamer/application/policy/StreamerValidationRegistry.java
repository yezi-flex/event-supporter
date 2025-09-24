package io.yezi.broadcast.domains.streamer.application.policy;

import java.util.List;

import org.springframework.stereotype.Component;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class StreamerValidationRegistry {
	private final List<StreamerValidationPolicy> streamerValidationPolicies;

	public void validate(CreateStreamerRequest request) {
		streamerValidationPolicies.forEach(it -> it.validate(request));
	}
}
