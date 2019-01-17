package com.games.toki.pot.orchestrator;


import com.games.toki.pot.orchestrator.cache.FlightsResponseLocalStore;
import com.games.toki.pot.service.BusinessFlightService;
import com.games.toki.pot.service.CheapFlightService;
import com.games.toki.pot.service.aggregator.FlightResponseConverterAndAggregator;
import com.games.toki.pot.service.response.BusinessFlight;
import com.games.toki.pot.service.response.CheapFlight;

import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Optional;

@Component
public class FlightSearchOrchestrator {

	private final CheapFlightService cheapFlightService;
	private final BusinessFlightService businessFlightService;

	private final FlightResponseConverterAndAggregator flightResponseConverterAndAggregator;
	private final FlightsResponseLocalStore flightsResponseLocalStore;

	@Autowired
	public FlightSearchOrchestrator(CheapFlightService cheapFlightService, BusinessFlightService businessFlightService,
									FlightResponseConverterAndAggregator flightResponseConverterAndAggregator,
									FlightsResponseLocalStore flightsResponseLocalStore) {

		this.cheapFlightService = cheapFlightService;
		this.businessFlightService = businessFlightService;
		this.flightResponseConverterAndAggregator = flightResponseConverterAndAggregator;
		this.flightsResponseLocalStore = flightsResponseLocalStore;
	}

	public Flux<Flight> searchFlights(SearchCriteria searchCriteria) {

		Flux<Flight> flightsResponseAsFlux;

		if (this.flightsResponseLocalStore.isCacheNotEmpty()) {

			flightsResponseAsFlux = this.flightsResponseLocalStore.get();

		} else {

			Flux<CheapFlight> cheapFlightFlux = this.cheapFlightService.search();
			Flux<BusinessFlight> businessFlightFlux = this.businessFlightService.search();

			flightsResponseAsFlux = flightResponseConverterAndAggregator.convertAndAggregate(cheapFlightFlux, businessFlightFlux);

			//todo this can be stored even after filtering/sorting/pagination against unique key generated out of search criteria
			this.flightsResponseLocalStore.save(flightsResponseAsFlux);
		}

		if (!Optional.of(searchCriteria).isPresent()) {
			return flightsResponseAsFlux;
		}

		return flightsResponseAsFlux;
	}

}
