package com.games.toki.pot.domain.mapper;

import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.service.response.CheapFlight;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

@Component
public class CheapFlightToFlightModelConverter {


	public Flux<Flight> convert(Flux<CheapFlight> cheapFlightFlux) {

		List<Flight> flightList = new ArrayList<>();

		if(cheapFlightFlux != null) {
			cheapFlightFlux.toStream().forEach(
					cheapFlight -> {
						flightList.add(new Flight(cheapFlight.getId(), cheapFlight.getDepartureFrom(), cheapFlight.getArrivalTo(),
								toDateTime(cheapFlight.getDepartureTime()), toDateTime(cheapFlight.getArrivalTime()), "cheap"));
					}
			);
		}

		return Flux.fromIterable(flightList);
	}

	private String toDateTime(String timeInMillis) {
		return new DateTime(Long.valueOf(timeInMillis)).withZone(DateTimeZone.UTC).toString();
	}

}
