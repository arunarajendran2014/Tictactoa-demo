package com.example.tictactao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.mapper.TictactaoBoardMapper;
import com.example.mapper.TurnRequestMapper;
import com.example.tictac.controller.TictactaoController;
import com.example.tictac.model.BoardMapper;
import com.example.tictac.request.TurnRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


@WebMvcTest(TictactaoController.class)
class TictactaoApplicationTests {
	
	@Autowired
    private MockMvc mvc;
	private static TictactaoBoardMapper tictactaoBoardMapper;
	private static TurnRequestMapper turnRequestMapper;
	
	@BeforeAll
	public static void beforeAll () {
		tictactaoBoardMapper = new TictactaoBoardMapper();
		turnRequestMapper = new TurnRequestMapper();
	}
	
	@Test
	void tictactaoTurnPostReturnSuccessResponse() throws Exception {
		TurnRequest request =  turnRequestMapper
				.readData("src/test/java/com/example/resources/TurnRequest.json");
		String data = asJsonString(request);
		mvc.perform(post("/turn").content(data).contentType(MediaType.APPLICATION_JSON_VALUE).accept(MediaType.APPLICATION_JSON_VALUE)).andExpect(status().isOk())
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.state").isNotEmpty());
	}
	

	private String asJsonString(Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	 

}
