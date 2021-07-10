package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.unicamp.mc322.trabalho.jogo.expansao.Expansao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Feitico;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

public class Jogo {
    private Scanner comandos = new Scanner(System.in);
    private Expansoes expansoes = new Expansoes();
    private DecksPadroes decksPadroes = new DecksPadroes(); //Decks padrões do jogo.
    private Map<String, Usuario> usuarios = new HashMap<String, Usuario>(); //Dicionario com jogadores e key = id do jogador
    private List<String> nicksUtilizados = new ArrayList<String>(); //Lista de nicks ja usados no jogo, usado para verificar se a tag ja está sendo usada;
    private BoardManager boardManager;
    private Mesa mesa;

    public void criarExpansao(String nomeExpansao, Regiao regiao) {
        if(!expansoes.getExpansoesMap().keySet().contains(regiao)) {
            expansoes.addExpansao(new Expansao(nomeExpansao, regiao));
        }
        else {
            throw new GameException("Região já possui uma expansão.");
        }

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
        Bot bot = new Bot(new Usuario("Bot"));

        this.realizarPartida(jogador, bot);
    }

    private void realizarPartida(Jogador j1, Jogador j2) {
        boolean partidaAcabou = false;
        this.mesa = new Mesa(j1, j2);
        this.boardManager = new BoardManager(mesa);
        Jogador jogador1;
        Jogador jogador2;
        do {
            //Ciclo de comandos enquanto a partida está acontecendo;
            jogador1 = obterJogador1(mesa);
            jogador2 = obterJogador2(mesa);
            partidaAcabou = boardManager.executarPassosJogo(jogador1,jogador2);
            if (!partidaAcabou) {//se a partida ainda nao acabou o jogador 2 agora assume o papel de atacante da rodada
                partidaAcabou = boardManager.executarPassosJogo(jogador2,jogador1);
            }
        } while (!partidaAcabou);

    }

    private Jogador obterJogador1(Mesa mesa) {
        return mesa.getJogadores()[0];
    }

    private Jogador obterJogador2(Mesa mesa) {
        return mesa.getJogadores()[1];
    }

    public Usuario criarUsuario(String nome) {
        Usuario novoUsuario = new Usuario(nome);
        do {
            // Faz com que não exista 2 ids iguais cadastrados;
            novoUsuario.criarId();
        } while (nicksUtilizados.contains(novoUsuario.getId()));
        nicksUtilizados.add(novoUsuario.getId());
        usuarios.put(novoUsuario.getId(), novoUsuario);
        for(Deck deck : decksPadroes.getDecksPadroes()) {
            novoUsuario.addNovoDeck(deck.getNome(), deck); //Adiciona os decks padões no usuário.
        }
        System.out.printf("Novo jogador cadastrado com o id: %s\n", novoUsuario.getId());
        return novoUsuario;
    }

    public void imprimirListaExpansoes() {
        expansoes.imprimirNomeExpansoes();
    }

    public void imprimirDecksUsuario(String idUsuario) {
    //Imprime os decks do usuario;
    }

    public void AddDeckPadrao(Deck deck, Regiao regiao) {
        //Adiciona um novo deck padrão no jogo, decks padrões são compostos de apenas uma regiao;
        deck.setarRegiaoCartas(regiao);
        this.decksPadroes.addDeckPadrao(deck);
    }

    private String getRespostaSimOuNao() {
        String resposta;
        do {
            resposta = comandos.nextLine();
        } while(!resposta.equals("y") && !resposta.equals("n"));
        return resposta;
    }

    private String getRespostaEditarDeck() {
        String resposta;
        do {
            resposta = comandos.nextLine();
        } while(!resposta.equals("Remover") && !resposta.equals("Add"));
        return resposta;
    }

    private Carta clonarCarta(Carta carta) {
        //Como clonar uma carta??
        if(carta.ehFeitico()) {
            return new Feitico(carta.getNome(), carta.getCusto(), carta.getListaEfeitos());
        }
        else {
            Monstro monstro = (Monstro) carta;
            return new Monstro(monstro.getNome(), monstro.getNome(), monstro.getCusto(), monstro.getVidaMaxima(), monstro.getAtaque(), );
        }
    }

    public void criarNovoDeck(String idUsuario, String nomeDeck) {
        //Cria um novo deck de cartas e é dado um nome para ele;
        Usuario usuario = usuarios.get(idUsuario);
        Deck novoDeck = new Deck(nomeDeck);
        System.out.println("Escolha as regioes do seu deck, no maximo 2 regiões:\n");
        expansoes.imprimirCartasExpansoes();
        System.out.println("\nEscolha suas cartas(digite a regiao e depois o nome da carta):");
        for(int i = 0; i < 40; i++) {
            String respostaRegiao = comandos.nextLine();
            String respostaNomeCarta = comandos.nextLine();
            Carta carta = expansoes.getCarta(Regiao.valueOf(respostaRegiao), respostaNomeCarta);
            System.out.println("\nDeseja visualizar essa carta? y/n");
            switch(this.getRespostaSimOuNao()) {
                case "y":
                    carta.imprimirCartaDetalhada();
                    break;

                case "n":
                    break;
            }
            //Verificar se pode ser adicionada*

            System.out.println("\nDeseja adicionar essa carta no deck? y/n");
            switch(this.getRespostaSimOuNao()) {
                case "y":
                    Carta cartaClone = clonarCarta(carta);
                    novoDeck.addCarta(cartaClone);
                    break;

                case "n":
                    break;
            }
            System.out.println("Carta adicionada!");
        }
        usuario.addNovoDeck(nomeDeck, novoDeck);
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
                Carta novaCarta = new Carta(); //Cria um clone da carta baseada na carta escolhida;
                deckEditado.addCarta(novaCarta);
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
