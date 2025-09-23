package io.yezi.broadcast.domains.streamer.application.policy;

import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;

public interface StreamerValidationPolicy {
	void validate(CreateStreamerRequest request);
}
