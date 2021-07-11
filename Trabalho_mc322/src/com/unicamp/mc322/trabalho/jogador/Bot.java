package com.unicamp.mc322.trabalho.jogador;

import java.util.Map;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;


public class Bot extends Jogador {
	//quando for para jogar vs computador criar classe bot, com metodos para jogar automatico;
	
	public Bot(Usuario usuario) {
		super(usuario);
		this.bot = true;
	}

	@Override
	protected Deck perguntarDeckUsuario(Map<String, Deck> listaDecks) {
		List<Deck> decks = new ArrayList<Deck>(usuario.getDecks().values());
		return decks.get(this.getNumeroRandom(decks.size()));
	}

	public int getNumeroRandom(int max) {
		//Obtem um numero random no intervalo fornecido;
		Random numero = new Random();
		return numero.nextInt(max);

	}
}
