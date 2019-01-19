package com.games.toki.pot.service;

import com.games.toki.pot.service.repository.BusinessFlightRepository;
import com.games.toki.pot.service.response.BusinessFlight;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class BusinessFlightService {
    BusinessFlightRepository repository;

    public BusinessFlightService(BusinessFlightRepository repository) {
        this.repository = repository;
    }

    public Flux<BusinessFlight> search() {

           return repository.searchAvailableFlights();
    }

}
