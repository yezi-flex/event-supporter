package io.yezi.broadcast.domains.streamer.domain.model;

import io.yezi.broadcast.common.jpa.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(
	name = "streamer",
	uniqueConstraints = {
		@UniqueConstraint(name = "UK_STREAMER_LOGIN_ID", columnNames = "login_id"),
		@UniqueConstraint(name = "UK_STREAMER_CHANNEL_ID", columnNames = "channel_id"),
		@UniqueConstraint(name = "UK_STREAMER_STREAM_URL", columnNames = "stream_url")
	}
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Streamer extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private Long channelId;

	@Column(nullable = false)
	private String loginId;

	@Column(nullable = false)
	private String nickname;

	@Column(nullable = false)
	private String streamUrl;

	@Builder(access = AccessLevel.PRIVATE)
	private Streamer(Long channelId, String loginId, String nickname, String streamUrl) {
		this.channelId = channelId;
		this.loginId = loginId;
		this.nickname = nickname;
		this.streamUrl = streamUrl;
	}

	public static Streamer of(Long channelId, String loginId, String nickname, String streamUrl) {
		return Streamer.builder()
			.channelId(channelId)
			.loginId(loginId)
			.nickname(nickname)
			.streamUrl(streamUrl)
			.build();
	}
}
