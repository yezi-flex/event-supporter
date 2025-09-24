package io.yezi.broadcast.domains.streamer.presentation;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.yezi.broadcast.domains.streamer.application.StreamerService;
import io.yezi.broadcast.domains.streamer.application.dto.CreateStreamerRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/streamer")
@RequiredArgsConstructor
public class StreamerController {
	static final String STREAMER_BASE_URI = "/streamer";
	static final String STREAMER_DETAILS_URI_FORMAT = STREAMER_BASE_URI + "/{id}";
	private final StreamerService streamerService;

	@PostMapping
	ResponseEntity<Void> register(@RequestBody CreateStreamerRequest request) {
		Long createdStreamerId = streamerService.create(request);
		URI createdStreamerUri = UriComponentsBuilder.fromPath(STREAMER_DETAILS_URI_FORMAT).build(createdStreamerId);

		return ResponseEntity.created(createdStreamerUri).build();
	}
}
