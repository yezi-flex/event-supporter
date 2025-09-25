package io.yezi.broadcast.config.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.yezi.broadcast.common.security.SecurityConfig;
import io.yezi.broadcast.domains.streamer.application.StreamerService;
import io.yezi.broadcast.domains.streamer.presentation.StreamerController;
import io.yezi.broadcast.domains.user.application.UserService;
import io.yezi.broadcast.domains.user.presentation.UserController;

@WebMvcTest(controllers = {
	StreamerController.class,
	UserController.class,
})
@AutoConfigureMockMvc
@Import(SecurityConfig.class)
public class WebMvcTestBase {
	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	protected ObjectMapper objectMapper;

	@MockitoBean
	protected StreamerService streamerService;
	
	@MockitoBean
	protected UserService userService;
}
