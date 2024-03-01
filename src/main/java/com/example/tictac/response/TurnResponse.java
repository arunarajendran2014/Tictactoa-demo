package com.example.tictac.response;

import java.util.Map;

import com.example.tictac.model.TictacPlayer;

public class TurnResponse {
    private Boolean gameOver = false;
    private TictacPlayer winner;
    private Map<String, String> state;
    private String status;

    public TurnResponse(TictacPlayer winner, Map<String, String> state) {
        if(winner != null) {
            this.winner = winner;
            this.gameOver = true;
	        this.status = winner.getPlayer() != "Draw" ?
	        		"Game is over !!, Winner is " + winner.getPlayer() : "Game is Draw !!";
        }
        this.state = state;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void setState(Map<String, String> state) {
        this.state = state;
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public Map<String, String> getState() {
        return state;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public TictacPlayer getWinner() {
		return winner;
	}

	public void setWinner(TictacPlayer winner) {
		this.winner = winner;
	}

    

}
