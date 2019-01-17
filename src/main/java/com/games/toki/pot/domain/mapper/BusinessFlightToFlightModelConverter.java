package com.games.toki.pot.domain.mapper;

import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.service.response.BusinessFlight;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessFlightToFlightModelConverter {

	public Flux<Flight> convert(Flux<BusinessFlight> businessFlightFlux) {

		List<Flight> flightList = new ArrayList<>();
		businessFlightFlux.toStream().forEach(
				businessFlight -> {

					flightList.add(new Flight(businessFlight.getId(),
							departureFrom(businessFlight.getDepartureFromArrivalTo()),
							arrivalTo(businessFlight.getDepartureFromArrivalTo()),
							businessFlight.getDepartureTime(), businessFlight.getArrivalTime(), "business"));
				}
		);

		return Flux.fromIterable(flightList);
	}
	
	private String departureFrom(String departureFromArrivalTo) {
		return departureFromArrivalTo.split(" -> ")[0];
	}

	private String arrivalTo(String departureFromArrivalTo) {
		return departureFromArrivalTo.split(" -> ")[1];
	}

}
