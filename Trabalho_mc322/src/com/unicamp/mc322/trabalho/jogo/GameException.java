package com.unicamp.mc322.trabalho.jogo;

public class GameException extends IllegalArgumentException {
	
	public GameException() {
		super();
	}
	
	public GameException(String messagem) {
		super(messagem);
	}
	
	public GameException(String messagem, Throwable causa) {
		super(messagem, causa);
	}
	
	public GameException(Throwable causa) {
		super(causa);
	}
}
