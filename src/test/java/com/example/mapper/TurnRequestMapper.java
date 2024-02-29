package com.example.mapper;

import java.io.File;
import java.io.IOException;

import com.example.tictac.request.TurnRequest;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TurnRequestMapper {
	
	public TurnRequest readData(String path) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			TurnRequest request = mapper.readValue(new File(path), TurnRequest.class);
			return request;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
