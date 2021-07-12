package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.unicamp.mc322.trabalho.jogo.expansao.Expansao;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.*;

public class Jogo {
    private Scanner comandos = new Scanner(System.in);
    private Expansoes expansoes = new Expansoes();
    private DecksPadroes decksPadroes = new DecksPadroes(); //Decks padrões do jogo.
    private Map<String, Usuario> usuarios = new HashMap<String, Usuario>(); //Dicionario com jogadores e key = id do jogador
    private List<String> idsUtilizados = new ArrayList<String>(); //Lista de nicks ja usados no jogo, usado para verificar se a tag ja está sendo usa

    public void criarExpansao(String nomeExpansao, Regiao regiao) {
        if (!expansoes.getExpansoesMap().containsKey(regiao)) {
            expansoes.addExpansao(new Expansao(nomeExpansao, regiao));
        } else {
            throw new GameException("Região já possui uma expansão.");
        }

    }

    public void addCartaNaExpansao(Regiao regiao, Carta novaCarta) {
        if (novaCarta.getRegiao() != null) {
            throw new GameException("Carta já existe em outra expansão.");
        } else {
            expansoes.addCarta(regiao, novaCarta);
        }

    }

    public void realizarPartidaJogadorVsJogador(String idUsuario1, String idUsuario2) {
        Jogador j1 = new Jogador(usuarios.get(idUsuario1));
        Jogador j2 = new Jogador(usuarios.get(idUsuario2));
        System.out.println("----PARTIDA ENTRE " + idUsuario1 + " E " + idUsuario2 + "----");
        //Embaralha deck dos jogadores;
        j1.embaralharDeck();
        j2.embaralharDeck();
        
        //puxa as cartas;
        this.puxarCartas(j1, j2);

        //Imprime mao dos jogadores;
        j1.imprimirMao();
        j2.imprimirMao();
        System.out.println("\n");

        //Opcao para trocar ate 4 cartas da mao;
        this.trocarMao(j1);
        this.trocarMao(j2);

        //Comeca o ciclo de turnos;
        this.realizarPartida(j1, j2);
    }

    public void realizarPartidaJogadorVsComputador(String idUsuario) {
        Jogador jogador = new Jogador(usuarios.get(idUsuario));
        Bot bot = new Bot(this.criarUsuario("Bot"));
        System.out.println("----PARTIDA ENTRE " + idUsuario + " E BOT----");
        //Embaralha deck do jogador e do bot;
        jogador.embaralharDeck();
        bot.embaralharDeck();
        
        //puxa as cartas;
        this.puxarCartas(jogador, bot);

        //Imprime mao dos jogadores;
        jogador.imprimirMao();
        bot.imprimirMao();
        System.out.println("\n");

        //Opcao para trocar ate 4 cartas da mao;
        this.trocarMao(jogador);

        //Comeca o ciclo de turnos;
        this.realizarPartida(jogador, bot);
    }

    public void realizarPartidaComputadorVsComputador() {
        Bot bot1 = new Bot(this.criarUsuario("Bot1"));
        Bot bot2 = new Bot(this.criarUsuario("Bot2"));
        System.out.println("----PARTIDA ENTRE BOTS----");
        //Embaralha deck dos bots;
        bot1.embaralharDeck();
        bot2.embaralharDeck();

        //puxa as cartas;
        this.puxarCartas(bot1, bot2);

        //Imprime mao dos jogadores;
        bot1.imprimirMao();
        bot2.imprimirMao();
        System.out.println("\n");

        //Comeca o ciclo de turnos;
        this.realizarPartida(bot1, bot2);
    }
    
    private void puxarCartas(Jogador jogador1, Jogador jogador2) {
        //Puxa as primeiras cartas
        for (int i = 0; i < 4; i++) {
            jogador1.puxarCarta();
            jogador2.puxarCarta();
        }  
    }

    private void realizarPartida(Jogador jogador1, Jogador jogador2) {
        boolean partidaAcabou;
        Mesa mesa = new Mesa(jogador1, jogador2);
        BoardManager boardManager = new BoardManager(mesa);
        
        do {
            //Ciclo de comandos enquanto a partida está acontecendo;
            jogador1 = obterJogador1(mesa);
            jogador2 = obterJogador2(mesa);
            System.out.println("\n>>>" + jogador1.getUsuario().getId() + " está atacando e " + jogador2.getUsuario().getId() + " está defendendo.\n");
            jogador1.setEstadoJogador(EstadoJogador.Atacante);
            jogador2.setEstadoJogador(EstadoJogador.Defensor);
            mesa.imprimirMesa();
            partidaAcabou = boardManager.executarPassosJogo(jogador1, jogador2);

            if (!partidaAcabou) {//se a partida ainda nao acabou o jogador 2 agora assume o papel de atacante da rodada
                System.out.println("\n>>>" + jogador2.getUsuario().getId() + " está atacando e " + jogador1.getUsuario().getId() + " está defendendo.<<<\n");
                System.out.println();
                jogador2.setEstadoJogador(EstadoJogador.Atacante);
                jogador1.setEstadoJogador(EstadoJogador.Defensor);
                mesa.imprimirMesa();
                partidaAcabou = boardManager.executarPassosJogo(jogador2, jogador1);

            }
        } while (!partidaAcabou);
        if(jogador1.getVida() <= 0 || jogador1.getDeckEscolhido().size() == 0){
            System.out.println(">>>>>" + jogador2.getUsuario().getId() + " voce ganhou, Parabens!!!");
        }
        if(jogador2.getVida() <= 0 || jogador2.getDeckEscolhido().size() == 0){
            System.out.println(">>>>>" + jogador1.getUsuario().getId() + " voce ganhou, Parabens!!!");
        }
    }

    private void trocarMao(Jogador jogador) {
        //Funcao que permite o jogador trocar de 0 a 4 cartas da sua mão no começo do jogo;
        System.out.println(jogador.getUsuario().getId() + ", voce deseja trocar alguma carta?(y/n)");
        switch (this.getRespostaSimOuNao()) {
            case "y":

                for(Carta carta : jogador.getMao()) {
                    System.out.println("> Carta:");
                    carta.imprimirCartaDetalhada();
                    System.out.println("\n"+ jogador.getUsuario().getId() + ", voce deseja trocar essa carta?(y/n)");

                    switch (this.getRespostaSimOuNao()) {
                        case "y":
                            jogador.trocarCarta(carta);
                            System.out.println("Nova mão:");
                            jogador.imprimirMao();
                            System.out.println();
                            break;

                        case "n":
                            break;
                    }

                }
                break;

            case "n":
                break;
        }

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
        } while (idsUtilizados.contains(novoUsuario.getId()));
        idsUtilizados.add(novoUsuario.getId());
        usuarios.put(novoUsuario.getId(), novoUsuario);
        for(Deck deck : decksPadroes.getDecksPadroes()) {
            novoUsuario.addNovoDeck(deck.getNome(), clonarDeck(deck)); //Adiciona os decks padões no usuário.
        }
        System.out.printf("Novo jogador cadastrado com o id: %s\n\n", novoUsuario.getId());
        return novoUsuario;
    }

    public void imprimirListaExpansoes() {
        expansoes.imprimirNomeExpansoes();
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
            if(!resposta.equals("y") && !resposta.equals("n")) {
                System.out.println("Resposta invalida, por favor, responda com (y/n).");
            }
        } while(!resposta.equals("y") && !resposta.equals("n"));
        return resposta;
    }

    private String getRespostaEditarDeck() {
        String resposta;
        do {
            resposta = comandos.nextLine();
            if(!resposta.equals("Remover") && !resposta.equals("Add")) {
                System.out.println("Resposta invalida, por favor, responda com (Remover/Add).");
            }
        } while(!resposta.equals("Remover") && !resposta.equals("Add"));
        return resposta;
    }

    private ArrayList<Traco> clonarTracos(ArrayList<Traco> tracos) {
        ArrayList<Traco> tracosClonado = new ArrayList<>();
        tracosClonado.addAll(tracos);
        return tracosClonado;
    }

    private Deck clonarDeck(Deck deck) {
        Deck deckClone = new Deck(deck.getNome());
        for(Carta carta : deck.getDeckStack()) {
            deckClone.addCarta(this.clonarCarta(carta));
        }
        return deckClone;
    }

    private Carta clonarCarta(Carta carta) {
        if(carta.ehFeitico()) {
            Feitico feiticoClonado = new Feitico(carta.getNome(), carta.getCusto(), carta.getListaEfeitos());
            feiticoClonado.setRegiao(carta.getRegiao());
            return feiticoClonado;
        }
        else {
            Monstro monstro = (Monstro) carta;
            ArrayList<Traco> tracos = clonarTracos(monstro.getTracos());
            if(monstro.ehCampeao()) {
                Campeao campeaoClonado = new Campeao(monstro.getNome(), monstro.getCusto(), monstro.getVidaMaxima(), monstro.getAtaque(), tracos, monstro.getAtaqueFuria(), monstro.getVidaFuria(), monstro.getListaEfeitos());
                campeaoClonado.setRegiao(carta.getRegiao());
                return campeaoClonado;
            }
            Monstro monstroClonado = new Monstro(monstro.getNome(), monstro.getCusto(), monstro.getVidaMaxima(), monstro.getAtaque(), tracos, monstro.getAtaqueFuria(), monstro.getVidaFuria(), monstro.getListaEfeitos());
            monstroClonado.setRegiao(carta.getRegiao());
            return monstroClonado;
        }
    }

    private boolean verificarSeCartaEhAdicionavel(Carta carta, Deck deck) {
        //Se a carta a ser adicionada for de uma regiao diferente de 2 outras no deck ela nao pode ser adicionada;
        return deck.getRegioes().size() != 2 || deck.getRegioes().contains(carta.getRegiao());
    }

    private void addCartaEmDeck(Deck deck) {
        System.out.println("Digite o nome da regiao e depois da carta que deseja adicionar.");
        boolean cartaPodeSerAdd;
        Carta carta;
        //Verificar se pode ser adicionada*
        do {

            do {
                carta = null;
                try {
                    System.out.println("Regiao:");
                    String respostaRegiao = comandos.nextLine();
                    System.out.println("Nome da carta:");
                    String respostaNomeCarta = comandos.nextLine();
                    carta = expansoes.getCarta(Regiao.valueOf(respostaRegiao), respostaNomeCarta);
                    if (carta == null) {
                        System.out.println("Nao existe carta com esse nome nessa regiao. Tente adicionar outra carta.");
                    }
                }
                catch (Exception IllegalArgumentException) {
                    System.out.println("Esta regiao nao existe, de uma nova regiao:");
                    String respostaRegiao = comandos.nextLine();
                }

            } while(carta != null);


            System.out.println("\nDeseja visualizar essa carta? y/n");
            switch(this.getRespostaSimOuNao()) {
                case "y":
                    carta.imprimirCartaDetalhada();
                    break;

                case "n":
                    break;
            }
            cartaPodeSerAdd = verificarSeCartaEhAdicionavel(carta, deck);
        }while(!cartaPodeSerAdd);



        System.out.println("\nDeseja adicionar essa carta no deck? y/n");
        switch(this.getRespostaSimOuNao()) {
            case "y":
                Carta cartaClone = clonarCarta(carta); //Cria um clone da carta baseada na carta escolhida;
                deck.addCarta(cartaClone);
                break;

            case "n":
                break;
        }
        System.out.println("Carta adicionada!");
    }

    public void criarNovoDeck(String idUsuario, String nomeDeck) {
        //Cria um novo deck de cartas e é dado um nome para ele;
        Usuario usuario = usuarios.get(idUsuario);
        Deck novoDeck = new Deck(nomeDeck);
        System.out.println("O deck pode ter cartas de no maximo 2 regioes.\n");
        expansoes.imprimirCartasExpansoes();

        for(int i = 0; i < 40; i++) {
            addCartaEmDeck(novoDeck);

        }
        usuario.addNovoDeck(nomeDeck, novoDeck);
    }

    public void editarDeck(String idUsuario, String nomeDeck) {
        Deck deckEditado = usuarios.get(idUsuario).getDeck(nomeDeck);

        System.out.println("Voce deseja adicionar ou remover cartas?(Remover/Add)");
        switch (this.getRespostaEditarDeck()) {
            case "Remover":
                //Remover carta
                System.out.println("Digite o nome da carta que deseja remover:\n");
                boolean cartaEstaNoDeck;
                do{
                    cartaEstaNoDeck = deckEditado.removerCarta(comandos.nextLine());
                }while(cartaEstaNoDeck);

                break;

            case "Add":
                addCartaEmDeck(deckEditado);
                break;
        }

    }

    public void deletarDeck(String idUsuario, String nomeDeck) {
        usuarios.get(idUsuario).deletarDeck(nomeDeck);
    }

    public void fecharJogo() {
        comandos.close();
    }

}
