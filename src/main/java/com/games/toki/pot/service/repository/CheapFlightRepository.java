package com.games.toki.pot.service.repository;


import com.games.toki.pot.service.response.CheapFlight;
import reactor.core.publisher.Flux;

public class CheapFlightRepository {

	public Flux<CheapFlight> searchAvailableFlights() {
		return Flux.just(new CheapFlight());
	}
}
