package io.yezi.broadcast.domains.user.presentation;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import io.yezi.broadcast.domains.user.application.UserService;
import io.yezi.broadcast.domains.user.application.dto.SignUpRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
	static final String USER_BASE_URI = "/user";
	private static final String USER_DETAIL_URI_FORMAT = USER_BASE_URI + "/{id}";
	private final UserService userService;

	@PostMapping("/sign-up")
	ResponseEntity<Void> signUp(@RequestBody SignUpRequest request) {
		Long registeredUserId = userService.create(request);
		URI registeredUserUri = UriComponentsBuilder.fromPath(USER_DETAIL_URI_FORMAT).build(registeredUserId);

		return ResponseEntity.created(registeredUserUri).build();
	}
}
