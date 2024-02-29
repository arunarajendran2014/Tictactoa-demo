package com.example.tictactao;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.mapper.TictactaoBoardMapper;
import com.example.tictac.model.BoardMapper;

@SpringBootTest
class TictactaoApplicationTests {
	
	
	private static TictactaoBoardMapper tictactaoBoardMapper;
	
	@BeforeAll
	public static void beforeAll () {
		tictactaoBoardMapper = new TictactaoBoardMapper();
	}
	
	

	@Test
	void tictactaoReturnSuccessResponse() {
		BoardMapper boardmapper = tictactaoBoardMapper.readData("src/test/java/com/example/resources/TictactaoBoardMapper.json");
		
	}

}
