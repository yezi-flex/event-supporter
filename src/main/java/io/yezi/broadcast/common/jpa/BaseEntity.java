package io.yezi.broadcast.common.jpa;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import jakarta.persistence.MappedSuperclass;
import lombok.Getter;

@Getter
@MappedSuperclass
public abstract class BaseEntity extends Timestamped {
	
	@CreatedBy
	private Long createdBy;

	@LastModifiedBy
	private Long updatedBy;

	private boolean deleted = false;

	public void delete() {
		this.deleted = true;
	}
}
