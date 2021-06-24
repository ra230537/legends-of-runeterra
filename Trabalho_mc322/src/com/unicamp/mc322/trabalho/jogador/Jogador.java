package com.unicamp.mc322.trabalho.jogador;

import java.util.Random;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Jogador {
	private String nick;
	private String id;
	private int vida = 20;
	private int mana = 1;
	private int manaFeitico = 0;
	private Carta[] mao = new Carta[10]; //na mão é possivel ter no maximo 10 cartas, qualquer outra carta retirada do baralho sera descartada;
	private Carta[] baralho= new Carta[40];
	
	public Jogador(String nickJogador) {
		this.nick = nickJogador;
	}
	
	private String obterTagRandom() {
		Random random = new Random();
		return String.format("%04d", random.nextInt(10000));
	}
	
	public void criarId() {
		this.id = nick + "#" + this.obterTagRandom();
	}
	
	public String getId() {
	//retorna o id do jogador(nome#DDDD) sendo DDDD 4 digitos ;
		return id;
	}
	
	public void imprimirId() {
	//METODO DE TESTE (***APAGAR DEPOIS)
		System.out.printf("%s", this.getId());
	}
	
	public void jogarTurno(Mesa mesa) {
		
	}

}
