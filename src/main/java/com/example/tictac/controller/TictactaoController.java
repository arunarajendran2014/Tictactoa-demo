package com.example.tictac.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.tictac.exception.PlayerNotFoundException;
import com.example.tictac.model.TictacPlayer;
import com.example.tictac.request.TurnRequest;
import com.example.tictac.response.TurnResponse;
import com.example.tictac.service.GameService;
import com.example.tictac.service.PlayerService;

@RestController
public class TictactaoController {
	
	private GameService gameService;
	private PlayerService playerService;
	
	@Autowired
	public TictactaoController(GameService gameService, PlayerService playerService) {
		super();
		this.gameService = gameService;
		this.playerService = playerService;
	}
	
	@GetMapping("/players")
    public List<TictacPlayer> getPlayers(@RequestParam(value = "id", defaultValue = "") String id) {
        return playerService.getPlayers();
    }
	
	@GetMapping("/state")
    public Map<String, String> getState() {
        return gameService.board;
    }
	
	 @PostMapping(value= "/turn", consumes = "application/json",
		        produces = "application/json")
	    public TurnResponse turn(@RequestBody @Valid TurnRequest request) throws PlayerNotFoundException {
	        if (gameService.isGameOver()) {
	            System.out.println("Game is already over !!");
	            return new TurnResponse(findWinner(), gameService.board);
	        } else {
	        	gameService.updateState(String.valueOf(request.getPosition()), playerService.getPlayer(request.getPlayerId()));
	            return new TurnResponse(findWinner(), gameService.board);
	        }
	    }
	
	private TictacPlayer findWinner() throws PlayerNotFoundException {
        String winner = gameService.toCheckWinner();
        TictacPlayer playerWinner;

        if (winner != null) {
            switch (winner) {
                case "X":
                	playerWinner = playerService.getPlayer(winner);
                    gameService.endGame(true);
                    break;
                case "O":
                    playerWinner = playerService.getPlayer(winner);
                    gameService.endGame(true);
                    break;
                case "draw":
                    playerWinner = new TictacPlayer("Draw");
                    gameService.endGame(true);
                    break;
                default:
                    playerWinner = null;
            }
            return playerWinner;
        }
        
        return null;
	}

}
