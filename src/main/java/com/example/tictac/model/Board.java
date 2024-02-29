package com.example.tictac.model;

import java.util.List;

import com.example.tictac.request.TurnRequest;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Board {

	private List<TurnRequest> board;

	public List<TurnRequest> getBoard() {
		return board;
	}

	public void setBoard(List<TurnRequest> board) {
		this.board = board;
	}

	@Override
	public String toString() {
		return "Board [board=" + board + "]";
	}

	
	
	
	
	
}
