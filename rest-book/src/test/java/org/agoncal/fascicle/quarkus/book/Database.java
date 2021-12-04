package org.agoncal.fascicle.quarkus.book;

import java.util.Collections;
import java.util.Map;

import org.testcontainers.containers.PostgreSQLContainer;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

public class Database implements QuarkusTestResourceLifecycleManager {
	
	private static final PostgreSQLContainer<?> DATABASE = new PostgreSQLContainer<>("postgres:12.1")
																	.withDatabaseName("books_database")
																	.withUsername("admin")
																	.withPassword("admin")
																	.withExposedPorts(12400);

	@Override
	public Map<String, String> start() {
		DATABASE.start();
		return Collections.singletonMap("quarkus.datasource.jdbc.url", DATABASE.getJdbcUrl());
	}

	@Override
	public void stop() {
		DATABASE.stop();
	}

}
