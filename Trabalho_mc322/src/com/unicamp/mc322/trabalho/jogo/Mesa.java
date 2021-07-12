package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;


public class Mesa {
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

		System.out.println("******" + getJogador1().getUsuario().getId() + "(" + getJogador1().getEstadoJogador().toString() + ")******" + "Vida do Nexus: " + getJogador1().getVida() + "******");
 		if(getJogador1().getMao().isEmpty()){
 			System.out.println("Mao de " + getJogador1().getUsuario().getId() + " esta vazia");
		}else{
			System.out.print("Mao do "+ getJogador1().getUsuario().getId() + ":" + "Mana Atual: " + getJogador1().getManaAtual() + " Mana Feitico: " + getJogador1().getManaFeitico());
 			for(int p = 0; p < mao1; p++){
				getJogador1().getMao().get(p).imprimirCarta();
				if(getJogador1().getMao().get(p).getCusto() <= (getJogador1().getMana() + getJogador1().getManaFeitico() ) && getJogador1().getMao().get(p).ehFeitico()){
					System.out.print("*");
				}else if(getJogador1().getMao().get(p).getCusto() <= getJogador1().getMana()){
					System.out.print("*");
				}
			}
 			System.out.println();
		}
		System.out.println("---------------------------------------------------------------------------------");
		System.out.println();
		if(getJogador1().getCartasEmCampo().isEmpty()){
			System.out.println("Nao existem monstros no campo de " + getJogador1().getUsuario().getId());
		}else{
			System.out.print("Campo de Jogador1: ");
			for(int i = 0; i < campo1; i++){
				getJogador1().getCartasEmCampo().get(i).imprimirCarta(i+1);
			}
			System.out.println();
		}

		if(getJogador1().getCartasBatalhando().isEmpty()){
			System.out.println("Nao existem monstros no campo de batalha de " + getJogador1().getUsuario().getId());
		}else{
			System.out.print("Campo de Batalha Jogador1:");
			if(batalha2 > batalha1){
				batalha1 = batalha2;
			}
			for(int j = 0; j < batalha1; j++){
				if(getJogador1().getCartasBatalhando().get(j) == null){
					System.out.print("  |"+ (j+1) + "[Posiçao Vazia]" + "|");
				}else{
					getJogador1().getCartasBatalhando().get(j).imprimirCarta(j+1);
				}

			}
			System.out.println();
		}
		System.out.println("------------------------------------------------------------------------");
		if(getJogador2().getCartasBatalhando().isEmpty()){
			System.out.println("Nao existem monstros no campo de Batalha de " + getJogador2().getUsuario().getId());
		}else{
			System.out.print("Campo de batalha Jogador2:");
			if(batalha1 > batalha2){
				batalha2 = batalha1;
			}
			for(int l = 0; l < batalha2; l++){
				if(getJogador2().getCartasBatalhando().get(l) == null){
					System.out.print("  |"+ (l+1) + "[Posiçao Vazia]" + "|");
				}else {
					getJogador2().getCartasBatalhando().get(l).imprimirCarta(l + 1);
				}
			}
			System.out.println();
		}

		if(getJogador2().getCartasEmCampo().isEmpty()){
			System.out.println("Nao existem monstros no campo de " + getJogador2().getUsuario().getId());
		}else{
			System.out.print("Campo de Jogador2:");
			for(int k = 0; k < campo2; k++){
				getJogador2().getCartasEmCampo().get(k).imprimirCarta(k+1);
			}
			System.out.println();
		}
		System.out.println();
		System.out.println("---------------------------------------------------------------------------------");
		if(getJogador2().getMao().isEmpty()){
			System.out.println("Mao de " + getJogador2().getUsuario().getId() + " esta vazia");
		}else{
			System.out.print("Mao do "+ getJogador2().getUsuario().getId() + ":" + "Mana Atual: " + getJogador2().getManaAtual() + " Mana Feitico: " + getJogador2().getManaFeitico());
			for(int y = 0; y < mao2; y++){
				getJogador2().getMao().get(y).imprimirCarta();
				if(getJogador2().getMao().get(y).getCusto() <= (getJogador2().getMana() + getJogador2().getManaFeitico() ) && getJogador2().getMao().get(y).ehFeitico()){
					System.out.print("*");
				}else if(getJogador2().getMao().get(y).getCusto() <= getJogador2().getMana()){
					System.out.print("*");
				}
			}
		}
		System.out.println("\n******" + getJogador2().getUsuario().getId() + "(" + getJogador2().getEstadoJogador().toString() + ")******" + "Vida do Nexus: " + getJogador2().getVida() + "******");
		System.out.println();
		System.out.println();
		System.out.println("////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////");
		System.out.println();
		System.out.println();
	}

}
