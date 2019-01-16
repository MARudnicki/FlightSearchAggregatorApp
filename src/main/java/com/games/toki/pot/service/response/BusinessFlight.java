package com.games.toki.pot.service.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessFlight {
	private String id;
	private String departureFromArrivalTo;

	private String departureTime;
	private String arrivalTime;

	public BusinessFlight(String id, String departureFromArrivalTo, String departureTime, String arrivalTime) {
		this.id = id;
		this.departureFromArrivalTo = departureFromArrivalTo;
		this.departureTime = departureTime;
		this.arrivalTime = arrivalTime;
	}
}
