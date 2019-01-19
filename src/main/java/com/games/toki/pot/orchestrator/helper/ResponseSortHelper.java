package com.games.toki.pot.orchestrator.helper;

import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Comparator;
import java.util.List;

/**
 * Created by mmundra on 1/16/19.
 */
@Component
public class ResponseSortHelper {

	public Flux<Flight> sort(Flux<Flight>  flightFlux, SearchCriteria searchCriteria) {

		String sortBy = searchCriteria.getSortBy();
		List<Flight> sortedFlightList = flightFlux.collectList().block();

		if("DepartureTime".equalsIgnoreCase(sortBy)) {
			sortedFlightList = flightFlux.collectSortedList(Comparator.comparing(Flight::getDepartureTime)).block();
		}

		if("Category".equalsIgnoreCase(sortBy)) {
			sortedFlightList = flightFlux.collectSortedList(Comparator.comparing(Flight::getCategory)).block();
		}

		return Flux.fromIterable(sortedFlightList);
	}
}
