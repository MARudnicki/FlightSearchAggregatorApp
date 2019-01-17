package com.games.toki.pot.service.aggregator;

import com.games.toki.pot.domain.mapper.BusinessFlightToFlightModelConverter;
import com.games.toki.pot.domain.mapper.CheapFlightToFlightModelConverter;
import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.service.response.BusinessFlight;
import com.games.toki.pot.service.response.CheapFlight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class FlightResponseConverterAndAggregator {

	private CheapFlightToFlightModelConverter cheapFlightToFlightModelConverter;
	private BusinessFlightToFlightModelConverter businessFlightToFlightModelConverter;

	@Autowired
	public FlightResponseConverterAndAggregator(CheapFlightToFlightModelConverter cheapFlightToFlightModelConverter,
												BusinessFlightToFlightModelConverter businessFlightToFlightModelConverter) {
		this.cheapFlightToFlightModelConverter = cheapFlightToFlightModelConverter;
		this.businessFlightToFlightModelConverter = businessFlightToFlightModelConverter;
	}

	public Flux<Flight> convertAndAggregate(Flux<CheapFlight> cheapFlightFlux, Flux<BusinessFlight> businessFlightFlux) {

		return Flux.merge(this.businessFlightToFlightModelConverter.convert(businessFlightFlux),
				this.cheapFlightToFlightModelConverter.convert(cheapFlightFlux));

	}

}
