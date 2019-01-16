package com.games.toki.pot.controller.tool;

import com.games.toki.pot.LiveTestBase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

public class HealthControllerLiveTest extends LiveTestBase {

	@Autowired
	HealthController healthController;

	@Test
	public void testHealthEndpoint() {
		WebTestClient webClient = WebTestClient.bindToController(healthController)
				.build()
				.mutate()
				.responseTimeout(Duration.ofMillis(30000))
				.build();

		webClient.get().uri("/flights/api/health")
				.exchange()
				.expectStatus().isOk()
				.expectBody();
	}
}
