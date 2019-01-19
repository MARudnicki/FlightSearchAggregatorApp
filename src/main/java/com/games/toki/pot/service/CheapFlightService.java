package com.games.toki.pot.service;

import com.games.toki.pot.service.repository.CheapFlightRepository;
import com.games.toki.pot.service.response.CheapFlight;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

/**
 * Created by mmundra on 1/13/19.
 */
@Service
public class CheapFlightService {
    private CheapFlightRepository repository;

    public CheapFlightService(CheapFlightRepository repository) {
        this.repository = repository;
    }

    public Flux<CheapFlight> search() {

           return repository.searchAvailableFlights();
    }

}
