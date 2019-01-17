package com.games.toki.pot.orchestrator;


import com.games.toki.pot.orchestrator.cache.FlightsResponseLocalStore;
import com.games.toki.pot.orchestrator.helper.ResponseFilterHelper;
import com.games.toki.pot.orchestrator.helper.ResponsePaginationHelper;
import com.games.toki.pot.orchestrator.helper.ResponseSortHelper;
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

	private final ResponseFilterHelper responseFilterHelper;
	private final ResponseSortHelper responseSortHelper;
	private final ResponsePaginationHelper responsePaginationHelper;

	@Autowired
	public FlightSearchOrchestrator(CheapFlightService cheapFlightService, BusinessFlightService businessFlightService,
									FlightResponseConverterAndAggregator flightResponseConverterAndAggregator,
									FlightsResponseLocalStore flightsResponseLocalStore,
									ResponseFilterHelper responseFilterHelper,
									ResponseSortHelper responseSortHelper,
									ResponsePaginationHelper responsePaginationHelper) {

		this.cheapFlightService = cheapFlightService;
		this.businessFlightService = businessFlightService;
		this.flightResponseConverterAndAggregator = flightResponseConverterAndAggregator;
		this.flightsResponseLocalStore = flightsResponseLocalStore;
		this.responseFilterHelper = responseFilterHelper;
		this.responseSortHelper = responseSortHelper;
		this.responsePaginationHelper = responsePaginationHelper;
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


		//fixme - issue with filter logic
		//flightsResponseAsFlux = responseFilterHelper.filter(flightsResponseAsFlux, searchCriteria);

		flightsResponseAsFlux = responseSortHelper.sort(flightsResponseAsFlux, searchCriteria);

		flightsResponseAsFlux = responsePaginationHelper.paginate(flightsResponseAsFlux, searchCriteria);


		return flightsResponseAsFlux;
	}

}
