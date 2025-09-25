package io.yezi.broadcast.domains.streamer.presentation;

import static io.yezi.broadcast.domains.streamer.presentation.StreamerController.*;
import static org.mockito.BDDMockito.*;
import static org.springframework.http.HttpHeaders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import io.yezi.broadcast.config.base.WebMvcTestBase;
import io.yezi.broadcast.domains.streamer.domain.StreamerFixtureGenerator;

@DisplayName("Controller:Streamer")
class StreamerControllerTest extends WebMvcTestBase {

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
