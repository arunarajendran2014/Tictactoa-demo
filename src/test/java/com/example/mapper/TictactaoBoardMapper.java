package com.example.mapper;

import java.io.File;
import java.io.IOException;

import com.example.tictac.model.BoardMapper;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TictactaoBoardMapper {
	
	public BoardMapper readData(String path) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			BoardMapper boardMapper = mapper.readValue(new File(path), BoardMapper.class);
			return boardMapper;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
