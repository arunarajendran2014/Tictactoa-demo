package com.example.tictac.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.tictac.exception.PlayerNotFoundException;
import com.example.tictac.model.TictacPlayer;

@Service
public class PlayerService {
	
	private List<TictacPlayer> players;

	public List<TictacPlayer> getPlayers() {
		return players;
	}
	
	public PlayerService() {
		players = new ArrayList<>();
		players.add(new TictacPlayer("X"));
		players.add(new TictacPlayer("O"));
		
	}
	
	 public TictacPlayer getPlayer(String player) throws RuntimeException {
	        List<TictacPlayer> collect = players.stream()
	                .filter(e -> e.getPlayer().equals(player))
	                .collect(Collectors.toList());
	        if (collect.size() > 0) {
	            return collect.get(0);
	        }
			throw new PlayerNotFoundException("Tictac player did not found");
	 }
	

}
