package com.games.toki.pot.controller.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchCriteria {


    private String departureFrom;
    private String arrivalTo;

    private String departureTime;
    private String arrivalTime;

    private String flightCategory;

    private String sortBy;

    private String pageNumber;
    private String pageSize;

    public SearchCriteria() {

    }

    public SearchCriteria(String departureFrom, String arrivalTo, String departureTime, String arrivalTime,
                          String flightCategory, String sortBy, String pageNumber, String pageSize) {
        this.departureFrom = departureFrom;
        this.arrivalTo = arrivalTo;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.flightCategory = flightCategory;
        this.sortBy = sortBy;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }

}
