package com.example.tictac.exception;

public class PlayerNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 8444186934561159568L;

	public PlayerNotFoundException(String message) {
        super(message);
    }

}
