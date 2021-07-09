package com.unicamp.mc322.trabalho.jogador;

import java.util.Random;

public class Bot extends Jogador {
	//quando for para jogar vs computador criar classe bot, com metodos para jogar automatico;
	
	public Bot(Usuario usuario) {
		super(usuario);
		this.bot = true;
	}

	public int getNumeroRandom(int max) {
		//Obtem um numero random no intervalo fornecido;
		Random numero = new Random();
		return numero.nextInt(max);

	}
}
