package com.unicamp.mc322.trabalho.jogador;

import java.util.Random;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Jogador {
	private String id;
	private int vida = 20;
	private int mana = 1;
	private int manaFeitico = 0;
	private Carta[] mao = new Carta[10]; //na m�o � possivel ter no maximo 10 cartas, qualquer outra carta retirada do baralho sera descartada;
	private Carta[] baralho= new Carta[40];
	
	public Jogador(String nomeJogador) {
		this.criarId(nomeJogador);
	}
	
	private String obterTagRandom() {
		Random random = new Random();
		return String.format("%04d", random.nextInt(10000));
	}
	
	private void criarId(String nome) {
		this.id = nome + "#" + this.obterTagRandom();
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
