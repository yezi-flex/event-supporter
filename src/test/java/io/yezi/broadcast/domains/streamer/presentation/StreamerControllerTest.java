package io.yezi.broadcast.domains.streamer.presentation;

import static io.yezi.broadcast.domains.streamer.presentation.StreamerController.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import io.yezi.broadcast.common.security.SecurityConfig;
import io.yezi.broadcast.domains.streamer.application.StreamerService;
import io.yezi.broadcast.domains.streamer.domain.StreamerFixtureGenerator;

@Import(SecurityConfig.class)
@DisplayName("Controller:Streamer")
@WebMvcTest(StreamerController.class)
@AutoConfigureMockMvc
class StreamerControllerTest {

	@MockitoBean
	private StreamerService streamerService;

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	@DisplayName("[POST:200] 스트리머 등록 API")
	void register() throws Exception {
		// Given
		var given = StreamerFixtureGenerator.스트리머_등록_요청_객체_생성();
		given(streamerService.create(given)).willReturn(1L);

		// When
		var actual = mockMvc.perform(post(STREAMER_BASE_URI)
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(given))
		);

		// Then
		actual.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(header().exists(LOCATION))
			.andDo(print());
	}

}
