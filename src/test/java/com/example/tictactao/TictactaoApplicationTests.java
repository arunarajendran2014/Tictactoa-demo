package com.example.tictactao;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.util.NestedServletException;

import com.example.mapper.TictactaoBoardMapper;
import com.example.mapper.TurnRequestMapper;
import com.example.tictac.controller.TictactaoController;
import com.example.tictac.exception.PlayerNotFoundException;
import com.example.tictac.model.BoardMapper;
import com.example.tictac.model.TictacPlayer;
import com.example.tictac.request.TurnRequest;
import com.example.tictac.response.TurnResponse;
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
	
	@Test
	void playerNotFoundExceptionTest() throws Exception {
		TurnRequest request = turnRequestMapper
				.readData("src/test/java/com/example/resources/TurnRequestException.json");
		String data = asJsonString(request);

		NestedServletException thrown = Assertions.assertThrows(NestedServletException.class,
				() -> mvc
						.perform(post("/turn").content(data).contentType(MediaType.APPLICATION_JSON_VALUE)
								.accept(MediaType.APPLICATION_JSON_VALUE))
						.andExpect(status().isBadRequest()).andReturn(),
				"Tictac player did not found");
		Assertions.assertTrue(thrown.getMessage()
				.contains("com.example.tictac.exception.PlayerNotFoundException: Tictac player did not found"));

	}
	
	@Test
	void getPlayersSuccessTest() throws Exception {
		MvcResult result = mvc.perform(
				get("/players").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		Assertions.assertEquals(200, result.getResponse().getStatus());
		Assertions.assertTrue(result.getResponse().getContentAsString()
				.contains("{\"player\":\"X\"},{\"player\":\"O\"}"));
	}
	
	@Test
	void getStateSuccessTest() throws Exception {
		MvcResult result = mvc.perform(
				get("/state").accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		Assertions.assertEquals(200, result.getResponse().getStatus());
		
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
