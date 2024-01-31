package com.ddas;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class DdasApplicationTests {
	@Test
	void contextLoads() {

	}

	@Test
	void contextLoads(ApplicationContext context) {
		assertNotNull(context);
	}
}
