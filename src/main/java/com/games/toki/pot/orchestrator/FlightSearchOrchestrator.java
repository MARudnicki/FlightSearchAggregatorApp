package com.games.toki.pot.orchestrator;


import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FlightSearchOrchestrator {


	public Flux<Flight> searchFlights(SearchCriteria searchCriteria) {
		return Flux.just(new Flight());
	}

}
