package io.yezi.broadcast.domains.user.domain.model;

import io.yezi.broadcast.common.jpa.Timestamped;
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
	name = "users",
	uniqueConstraints = @UniqueConstraint(name = "UK_USER_USERNAME", columnNames = "username")
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User extends Timestamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private UserRole role = UserRole.STREAMER;

	@Builder(access = AccessLevel.PRIVATE)
	private User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public static User of(String username, String password) {
		return User.builder()
			.username(username)
			.password(password)
			.build();
	}
}
