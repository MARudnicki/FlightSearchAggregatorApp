package com.games.toki.pot.controller.tool;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("flights/api")
public class HealthController {

	@GetMapping("/health")
	public Mono<String> sayHello() {
		return Mono.just("green");
	}
}
