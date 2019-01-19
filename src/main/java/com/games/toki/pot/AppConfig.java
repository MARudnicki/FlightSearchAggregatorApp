package com.games.toki.pot;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan({"com.toki.pot.service", "com.toki.pot.orchestrator.cache", "com.toki.pot.domain.mapper"})
public class AppConfig {
}
