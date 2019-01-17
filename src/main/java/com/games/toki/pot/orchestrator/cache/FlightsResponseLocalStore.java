package com.games.toki.pot.orchestrator.cache;

import com.games.toki.pot.domain.model.Flight;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FlightsResponseLocalStore {

	private Flux<Flight> cachedAggregatedFlightFlux;

	public void save(Flux<Flight> aggregatedFlightList) {
		this.cachedAggregatedFlightFlux = aggregatedFlightList;

	}

	public Flux<Flight> get() {
		return this.cachedAggregatedFlightFlux;
	}

	public boolean isCacheNotEmpty() {
		return cachedAggregatedFlightFlux != null;
	}
}
