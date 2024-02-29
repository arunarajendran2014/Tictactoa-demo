package com.example.tictac.model;

import java.util.List;

import com.example.tictac.request.TurnRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

	private List<TurnRequest> turnRequestList;

	public List<TurnRequest> getTurnRequestList() {
		return turnRequestList;
	}

	public void setTurnRequestList(List<TurnRequest> turnRequestList) {
		this.turnRequestList = turnRequestList;
	}

	@Override
	public String toString() {
		return "Board [turnRequestList=" + turnRequestList + "]";
	}
	
	
	
	
}
