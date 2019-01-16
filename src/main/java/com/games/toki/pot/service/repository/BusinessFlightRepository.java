package com.games.toki.pot.service.repository;


import com.games.toki.pot.service.response.BusinessFlight;
import reactor.core.publisher.Flux;

public class BusinessFlightRepository {

	public Flux<BusinessFlight> searchAvailableFlights() {
		return Flux.just(new BusinessFlight());
	}
}
