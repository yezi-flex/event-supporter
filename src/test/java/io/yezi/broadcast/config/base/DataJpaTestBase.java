package io.yezi.broadcast.config.base;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;

import io.yezi.broadcast.common.jpa.JpaConfig;
import jakarta.persistence.EntityManager;

@Import(JpaConfig.class)
@DataJpaTest
public class DataJpaTestBase extends TestBase {
	@Autowired
	protected EntityManager entityManager;

	protected void flushAndClear() {
		entityManager.flush();
		entityManager.clear();
	}

	protected <T> T flushAndClear(Supplier<T> supplier) {
		T result = supplier.get();
		flushAndClear();
		return result;
	}

	protected void flushAndClear(Runnable runnable) {
		runnable.run();
		flushAndClear();
	}
}
