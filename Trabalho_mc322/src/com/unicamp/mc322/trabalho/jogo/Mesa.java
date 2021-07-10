package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

import java.util.ArrayList;

public class Mesa {
	//private Carta[][] CartasNaMesa = new Carta[4][6];
	private Jogador[] jogadores	= new Jogador[2];

	public Mesa(Jogador j1, Jogador j2) {
		jogadores[0] = j1;
		jogadores[1] = j2;
	}

	public Jogador getJogador1() {
		return jogadores[0];
	}

	public Jogador getJogador2() {
		return jogadores[1];
	}

	public Jogador[] getJogadores(){
		return jogadores;
	}

	public void imprimirMesa(){
		int campo1 = getJogador1().getCartasEmCampo().size();
		int batalha1 = getJogador1().getCartasBatalhando().size();
		int campo2 = getJogador2().getCartasEmCampo().size();
		int batalha2 = getJogador2().getCartasBatalhando().size();
		int mao1 = getJogador1().getMao().size();
		int mao2 = getJogador2().getMao().size();

 		if(getJogador1().getMao().isEmpty()){
 			System.out.println("Mao do jogador1 esta vazia");
		}else{
 			for(int p = 0; p <= mao1; p++){
 				getJogador1().getMao().get(p).imprimirCarta();
 				if(getJogador1().getMao().get(p).getCusto() < getJogador1().getMana()){
					System.out.println("*");
				}else{
 					System.out.println();
				}
			}
		}
		if(getJogador1().getCartasEmCampo().isEmpty()){
			System.out.println("Nao existem monstros no campo do jogador 1");
		}else{
			for(int i = 0; i <= campo1; i++){
				getJogador1().getCartasEmCampo().get(i).imprimirCarta(i+1);
				System.out.println();
			}
		}

		if(getJogador1().getCartasBatalhando().isEmpty()){
			System.out.println("Nao existem monstros no campo de batalha do jogador 1");
		}else{
			for(int j = 0; j <= batalha1; j++){
				getJogador1().getCartasBatalhando().get(j).imprimirCarta(j+1);
				System.out.println();
			}
		}

		if(getJogador2().getCartasEmCampo().isEmpty()){
			System.out.println("Nao existem monstros no campo do jogador 2");
		}else{
			for(int l = 0; l <= batalha2; l++){
				getJogador2().getCartasBatalhando().get(l).imprimirCarta(l+1);
				System.out.println();
			}
		}

		if(getJogador2().getCartasBatalhando().isEmpty()){
			System.out.println("Nao existem monstros no campo de batalha do jogador 2");
		}else{
			for(int k = 0; k <= campo2; k++){
				getJogador2().getCartasEmCampo().get(k).imprimirCarta(k+1);
				System.out.println();
			}
		}
		if(getJogador2().getMao().isEmpty()){
			System.out.println("Mao do jogador2 esta vazia");
		}else{
			for(int y = 0; y <= mao2; y++){
				getJogador2().getMao().get(y).imprimirCarta();
				if(getJogador2().getMao().get(y).getCusto() < getJogador2().getMana()){
					System.out.println("*");
				}else{
					System.out.println();
				}
			}
		}
	}
}
