package com.games.toki.pot.controller.api;


import com.games.toki.pot.domain.model.Flight;
import com.games.toki.pot.orchestrator.FlightSearchOrchestrator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;


@RestController
@RequestMapping("flights/api")
public class FlightSearchController {

    FlightSearchOrchestrator flightSearchOrchestrator;

    public FlightSearchController(FlightSearchOrchestrator flightSearchOrchestrator) {
        this.flightSearchOrchestrator = flightSearchOrchestrator;
    }

    @GetMapping("/search")
    public Flux<Flight> searchFlights(@ModelAttribute SearchCriteria searchCriteria) {

        return flightSearchOrchestrator.searchFlights(searchCriteria);
    }

}
