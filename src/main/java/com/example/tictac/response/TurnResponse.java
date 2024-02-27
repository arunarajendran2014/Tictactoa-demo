package com.example.tictac.response;

import java.util.Map;

import com.example.tictac.model.TictacPlayer;

public class TurnResponse {
    public Boolean gameOver = false;
    public TictacPlayer winner;
    public Map<String, String> state;

    public TurnResponse(TictacPlayer winner, Map<String, String> state) {
        if(winner != null) {
            this.winner = winner;
            this.gameOver = true;
	        System.out.println("Game is over !!, Winner is " + winner.getPlayer());
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


}
