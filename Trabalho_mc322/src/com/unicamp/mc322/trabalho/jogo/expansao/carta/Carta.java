package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import java.util.ArrayList;
import java.util.Collections;

import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.Regiao;
import com.unicamp.mc322.trabalho.jogador.Jogador;

public class Carta {
	private boolean temBarreira;
	private boolean feitico; //False = monstro, true = feitiço
	private String nome;
	private int custo;
	private Regiao regiao;
	private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); //uma carta pode ter mais de um Efeito

	protected Carta(String nome, int custo, boolean feitico, Efeito... efeitos){
		this.feitico = feitico;
        this.nome = nome;
        this.custo = custo;
        Collections.addAll(listaEfeitos,efeitos);
	}

	protected Carta(String nome, int custo, boolean feitico){
		this.feitico = feitico;
		this.nome = nome;
		this.custo = custo;
	}

	protected Carta(String nome, int custo, boolean feitico, ArrayList<Efeito> efeitos){
		this.feitico = feitico;
		this.nome = nome;
		this.custo = custo;
		this.listaEfeitos = efeitos;
	}

	public void setRegiao(Regiao regiao) {
		this.regiao = regiao;
	}

	public Regiao getRegiao() {
		return regiao;
	}

	public void imprimirCarta() {
		System.out.print("  |"+ this.getNome() + "(" + this.getCusto() + ")" + "|");
	}

	public void imprimirCartaDetalhada(){

	}
	public void imprimirNome() {
		//...
	}

	protected String getTextoEfeitos() {
		//ObterEfeitos pelo nome; *****
		String info = "";
		for(int i = 0; i < getListaEfeitos().size(); i++) {
			info += getListaEfeitos().get(i).getTipoEfeito();
			if((i+1) % 3 == 0) {
				info = "\n\r|";
			}
			else {
				info += ", ";
			}
		}
		return info;
	}


	/**
	 *
	 * @param efeito
	 * recebe um dos efeitos que a carta possui e que foi permitido ser usado pelo board manager
	 * manda a mensagem para a classe efeito de que ela pode usa-lo.
	 */
	public void ativarEfeito(Efeito efeito, Jogador jogador, Mesa mesa, Carta carta){
		efeito.usarEfeito(jogador, mesa, carta);
	}

	public ArrayList<Efeito> getListaEfeitos() {
		return listaEfeitos;
	}

	public int getCusto() {
		return custo;
	}

	public String getNome() {
		return nome;
	}

	public void SetTemBarreira(){
		this.temBarreira = true;
	}

	public boolean ehFeitico() {
		return feitico;
	}
}
