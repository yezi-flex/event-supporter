package io.yezi.broadcast.domains.user.presentation;

import static io.yezi.broadcast.domains.user.presentation.UserController.*;
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
import io.yezi.broadcast.domains.user.domain.UserFixtureGenerator;

@DisplayName("Controller:User")
class UserControllerTest extends WebMvcTestBase {

	@Test
	@DisplayName("[POST:200] 회원 가입 API")
	void register() throws Exception {
		// Given
		var given = UserFixtureGenerator.회원_가입_요청_객체_생성();
		given(userService.create(given)).willReturn(1L);

		// When
		var actual = mockMvc.perform(post(USER_BASE_URI + "/sign-up")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(given))
		);

		// Then
		actual.andExpect(MockMvcResultMatchers.status().isCreated())
			.andExpect(header().exists(LOCATION))
			.andDo(print());
	}
}
