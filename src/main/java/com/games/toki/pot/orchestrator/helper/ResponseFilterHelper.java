package com.games.toki.pot.orchestrator.helper;

import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ResponseFilterHelper {

	public Flux<Flight> filter(Flux<Flight>  flightFlux, SearchCriteria searchCriteria) {
		List<Flight> initialFlightList = flightFlux.collectList().block();
		List<Flight> filteredFlightList =
				initialFlightList.stream()
						.filter(flight -> (StringUtils.isNotEmpty(searchCriteria.getDepartureFrom()) && searchCriteria.getDepartureFrom().equalsIgnoreCase(flight.getDepartureFrom())))
						/*.filter(flight -> (StringUtils.isNotEmpty(searchCriteria.getDepartureTime()) && searchCriteria.getDepartureTime().equalsIgnoreCase(flight.getDepartureTime())))
						.filter(flight -> (StringUtils.isNotEmpty(searchCriteria.getArrivalTime()) && searchCriteria.getArrivalTime().equalsIgnoreCase(flight.getArrivalTime())))
						.filter(flight -> (StringUtils.isNotEmpty(searchCriteria.getFlightCategory()) && searchCriteria.getFlightCategory().equalsIgnoreCase(flight.getCategory())))
						.filter(flight -> (StringUtils.isNotEmpty(searchCriteria.getArrivalTo()) && searchCriteria.getArrivalTo().equalsIgnoreCase(flight.getArrivalTo())))*/
						.collect(Collectors.toList());
		return Flux.fromIterable(filteredFlightList);

	}


}
