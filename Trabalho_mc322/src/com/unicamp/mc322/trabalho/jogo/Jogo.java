package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogador.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.unicamp.mc322.trabalho.jogador.Deck;

public class Jogo {
    private Scanner comandos = new Scanner(System.in);
    private Expansoes expansoes = new Expansoes();
    private Map<String, Usuario> Usuarios = new HashMap<String, Usuario>(); //Dicionario com jogadores e key = id do jogador
    private List<String> nicksUtilizados = new ArrayList<String>(); //Lista de nicks ja usados no jogo, usado para verificar se a tag ja está sendo usada;
    private BoardManager boardManager;
    private Mesa mesa;

    public Jogo(Usuario user1, Usuario user2) {
        Jogador j1 = new Jogador(user1);
        Jogador j2 = new Jogador(user2);
        Mesa mesa = new Mesa(j1, j2);
        this.mesa = mesa;
        BoardManager boardManager = new BoardManager(mesa);
        this.boardManager = boardManager;
    }

    public void realizarPartida() {
        boolean partidaAcabou = false;

        do {
            //Ciclo de comandos enquanto a partida está acontecendo;
            Jogador jogador1 = obterJogador1(mesa);
            Jogador jogador2 = obterJogador2(mesa);
            partidaAcabou = boardManager.executarPassosJogo(jogador1,jogador2, mesa);
            if (!partidaAcabou) {//se a partida ainda nao acabou o jogador 2 agora assume o papel de atacante da rodada
                partidaAcabou = boardManager.executarPassosJogo(jogador2,jogador1, mesa);
            }
        } while (!partidaAcabou);

    }

    private Jogador obterJogador1(Mesa mesa) {
        return mesa.getJogadores()[0];
    }

    private Jogador obterJogador2(Mesa mesa) {
        return mesa.getJogadores()[1];
    }

    public void criarUsuario(String nome) {
        Usuario novoUsuario = new Usuario(nome);
        do {
            // Faz com que não exista 2 ids iguais cadastrados;
            novoUsuario.criarId();
        } while (nicksUtilizados.contains(novoUsuario.getId()));
        nicksUtilizados.add(novoUsuario.getId());
        Usuarios.put(novoUsuario.getId(), novoUsuario);
        System.out.printf("Novo jogador cadastrado com o id: %s\n", novoUsuario.getId());

    }

    public void criarNovoDeck(String idJogador, String nomeDeck) {
        //Cria um novo deck de cartas e é dado um nome para ele;
        Usuario Usuario = Usuarios.get(idJogador);
        Deck novoDeck = new Deck(nomeDeck);
        System.out.println("Escolha as regioes do seu deck, no maximo 2 regiões:\n");
        expansoes.imprimirExpansoes();
        System.out.println("\n2 Regiões restantes.");
        //...
        //...
        Usuario.addNovoDeck(nomeDeck, novoDeck);
    }

    public Expansoes getExpansoes() {
        return expansoes;
    }

    public void fecharJogo() {
        comandos.close();
    }

}
