package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Bot;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogador.Usuario;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.unicamp.mc322.trabalho.jogador.Deck;
import com.unicamp.mc322.trabalho.jogo.expansao.Expansao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Jogo {
    private Scanner comandos = new Scanner(System.in);
    private Expansoes expansoes = new Expansoes();
    private Map<String, Usuario> usuarios = new HashMap<String, Usuario>(); //Dicionario com jogadores e key = id do jogador
    private List<String> nicksUtilizados = new ArrayList<String>(); //Lista de nicks ja usados no jogo, usado para verificar se a tag ja está sendo usada;
    private BoardManager boardManager;
    private Mesa mesa;

    public void criarExpansao(String nomeExpansao, Regiao regiao) {
        expansoes.addExpansao(new Expansao(nomeExpansao, regiao));
    }

    public void addCartaNaExpansao(Regiao regiao, Carta novaCarta) {
        if(novaCarta.getRegiao() != null) {
            throw new GameException("Carta já existe em outra expansão.");
        }
        else {
            expansoes.addCarta(regiao, novaCarta);
        }

    }

    public void realizarPartidaVsJogador(String idUsuario1, String idUsuario2) {
        Jogador j1 = new Jogador(usuarios.get(idUsuario1));
        Jogador j2 = new Jogador(usuarios.get(idUsuario2));

        this.realizarPartida(j1, j2);
    }

    public void realizarPartidaVsComputador(String idUsuario) {
        Jogador jogador = new Jogador(usuarios.get(idUsuario));
        Bot bot = new Bot();

        this.realizarPartida(jogador, bot);
    }

    private void realizarPartida(Jogador j1, Jogador j2) {
        boolean partidaAcabou = false;
        this.mesa = new Mesa(j1, j2);
        this.boardManager = new BoardManager(mesa);

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
        usuarios.put(novoUsuario.getId(), novoUsuario);
        System.out.printf("Novo jogador cadastrado com o id: %s\n", novoUsuario.getId());

    }

    public void imprimirListaExpansoes() {
        expansoes.imprimirNomeExpansoes();
    }

    public void imprimirDecksUsuario(String idUsuario) {
    //Imprime os decks do usuario;
    }

    public void criarNovoDeck(String idUsuario, String nomeDeck) {
        //Cria um novo deck de cartas e é dado um nome para ele;
        Usuario usuario = usuarios.get(idUsuario);
        Deck novoDeck = new Deck(nomeDeck);
        System.out.println("Escolha as regioes do seu deck, no maximo 2 regiões:\n");
        expansoes.imprimirCartasExpansoes();
        System.out.println("\nEscolha suas cartas(digite a regiao e depois o nome da carta):");
        for(int i = 0; i < 40; i++) {

        }
        //...
        //...
        usuario.addNovoDeck(nomeDeck, novoDeck);
    }

    private String getRespostaEditarDeck() {
        String resposta;
        do {
            resposta = comandos.nextLine();
        } while(!resposta.equals("Remover") && !resposta.equals("Add"));
        return resposta;
    }

    public void editarDeck(String idUsuario, String nomeDeck) {
        Deck deckEditado = usuarios.get(idUsuario).getDeck(nomeDeck);

        System.out.println("Voce deseja adicionar ou remover cartas?(Remover/Add)");
        String resposta = this.getRespostaEditarDeck();
        switch (resposta) {
            case "Remover":
                //Remover carta
                System.out.println("Digite o nome da carta que deseja remover:\n");
                deckEditado.removerCarta(comandos.nextLine());
                break;

            case "Add":
                //Add carta
                System.out.println("Digite o nome da carta que deseja adicionar:\n");
                deckEditado.addCarta(comandos.nextLine());
                break;
        }



    }

    public void deletarDeck(String idUsuario, String nomeDeck) {
        usuarios.get(idUsuario).deletarDeck(nomeDeck);
    }

    public Expansoes getExpansoes() {
        return expansoes;
    }

    public void fecharJogo() {
        comandos.close();
    }

}
