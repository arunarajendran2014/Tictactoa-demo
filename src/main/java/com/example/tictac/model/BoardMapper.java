package com.example.tictac.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BoardMapper {
	
	private Board boardMapper;

	public Board getBoardMapper() {
		return boardMapper;
	}

	public void setBoardMapper(Board boardMapper) {
		this.boardMapper = boardMapper;
	}

	
	
	

}
