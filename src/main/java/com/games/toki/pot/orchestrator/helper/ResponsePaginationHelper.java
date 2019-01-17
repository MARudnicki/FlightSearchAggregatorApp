package com.games.toki.pot.orchestrator.helper;

import com.games.toki.pot.controller.api.SearchCriteria;
import com.games.toki.pot.domain.model.Flight;
import org.apache.commons.lang.math.NumberUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mmundra on 1/16/19.
 */
@Component
public class ResponsePaginationHelper {

	public Flux<Flight> paginate(Flux<Flight>  flightFlux, SearchCriteria searchCriteria) {

		if(flightFlux == null || flightFlux.collectList().block().size() == 0) {
			return flightFlux;
		}

		if(flightFlux != null && flightFlux.collectList().block().size() <= 2) {
		    return flightFlux;
		}

		if(flightFlux != null && flightFlux.collectList().block().size() < pageSize(searchCriteria)) {
			return flightFlux;
		}

		if(flightFlux != null && flightFlux.collectList().block().size() < pageSize(searchCriteria) * pageNumber(searchCriteria)) {
			return flightFlux;
		}

		List<Flight> paginatedFlightList = flightFlux.toStream().skip(skipElements(searchCriteria))
				.limit(pageSize(searchCriteria))
				.collect(Collectors.toList());
		return Flux.fromIterable(paginatedFlightList);
	}

	private int skipElements(SearchCriteria searchCriteria) {
		return pageNumber(searchCriteria) * pageSize(searchCriteria);
	}

	private int pageNumber(SearchCriteria searchCriteria) {
		String pageNumberInString = searchCriteria.getPageNumber();
		int pageNumber = 2;
		if(NumberUtils.isNumber(pageNumberInString) && NumberUtils.toInt(pageNumberInString) > 2) {
			pageNumber = NumberUtils.toInt(pageNumberInString) - 1;
		}
		return pageNumber;
	}

	private int pageSize(SearchCriteria searchCriteria) {
		String pageSizeInString = searchCriteria.getPageSize();
		int pageSize = 2;
		if(NumberUtils.isNumber(pageSizeInString) && NumberUtils.toInt(pageSizeInString) > 0) {
			pageSize = NumberUtils.toInt(pageSizeInString);
		}
		return pageSize;
	}
}
