package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardManager {
    Mesa mesa;
    /*		1) jogador 1 e 2 puxam uma carta do deck

             2)jogador 1 decide jogar uma carta
             -se a carta for jogada o board manager ve se a carta tem o efeito possivel de ser utlizado, ou seja um efeito que é habilitado assim que a carta eh evocada
             - Diminui a mana do jogador 1

             3)jogador 2 decide jogar uma carta
             -leitura da carta lançada pelo board manager
             //esse procedimento se repete até o momento que o jogador 1 (atacante) resolver fazer seu ataque
             -Diminui a mana do jogador 2

             4)jogador 1 escolhe quais monstros irão atacar
             -para cada monstro que o jogador 1 coloca no ataque o board manager verifica se ele possui algum efeito que possa ser usado antes do ataque (ex: atacar nexus),
             caso o monstro possua, o board manager vai se encarregar de fazer o monstro executar seu efeito de alguma forma

             5)jogador 2 escolhe quais monstros vão defender
             - para cada monstro que o jogador 2... obs: ainda não há efeito de monstro que funcione no momento da defesa, mas nao quer dizer que não possa existir

             6)cada carta do jogador 1 ataca enquanto os do jogador 2 defendem
             -board manager verifica efeito tanto de pos ataque, no caso do jogador 1 quanto de pos defesa, no caso do jogador 2
             -Se nao tiver nenhuma carta defendendo o dano é direto no nexus

             7)fim da rodada
             - o board manager le novamente todas as cartas para ver se alguma possui efeito que é ativado apos uma rodada de ataque/defesa.

             8)rodada é finalizada as posições de ataque e defesa se invertem, a mana que sobrou de cada jogador é adicionada a mana de feitiço.
            */
    public BoardManager(Mesa mesa) {
        this.mesa = mesa;
    }

    public boolean executarPassosJogo(Jogador jogadorAtacante, Jogador jogadorDefensor, Mesa mesa) {

        realizarCompra(jogadorAtacante, jogadorDefensor);

        permitirJogarCarta(jogadorAtacante, mesa, MomentosDoTurno.APOS_INVOCACAO);
        if(jogadorDefensorMorreu(jogadorDefensor)){
            return false;
        }

        permitirJogarCarta(jogadorDefensor, mesa, MomentosDoTurno.APOS_INVOCACAO);
        if(jogadorAtacanteMorreu(jogadorAtacante)){
            return false;
        }
        //so vai escolher os monstros que vao atacar
        escolherMonstrosAtaque(jogadorAtacante,mesa);
        //so vai escolher os monstros que vao defender
        escolherMonstrosDefesa(jogadorDefensor,mesa);
        //permite que o jogador ataque com seus monstros e os defensores defendam ao mesmo tempo, verifica efeito dos dois
        permitirBatalha(jogadorAtacante,mesa,MomentosDoTurno.ANTES_BATALHA);
        if (jogadorDefensorMorreu(jogadorDefensor)){
            return false;
        }
        //ve se os monstros que restam tem algum efeito pos batalha e tira os monstros mortos de campo
        verificarMonstrosPosBatalha(mesa,MomentosDoTurno.APOS_BATALHA);
        //inverter as posiçoes de quem é o atacante e quem é o defensor
        return executarPassosJogo(jogadorDefensor,jogadorAtacante,mesa);


    }

    private void escolherMonstrosAtaque(Jogador jogadorAtacante, Mesa mesa) {
        //perguntar a ele quais cartas ele quer colocar pro ataque (em ordem)
        ArrayList <String> listaCartasAtaque = interagirComUsuario();
        colocarCartasAtaque(jogadorAtacante,listaCartasAtaque);
    }


    private ArrayList<String> interagirComUsuario() {
        String nomeMonstro;
        ArrayList <String> listaCartasAtaque = new ArrayList<>();
        System.out.print("Escolha em ordem as cartas que você colocará em ataque. Digite 'sair' para finalizar\n");
        Scanner scan = new Scanner(System.in);
        do{
            nomeMonstro = scan.nextLine();
            if(naoEscreveuSair(nomeMonstro) && ehMonstroValido()){
                listaCartasAtaque.add(nomeMonstro);
            }
        }while(naoEscreveuSair(nomeMonstro));
        scan.close();
        return listaCartasAtaque;
    }

    private boolean naoEscreveuSair(String palavra){
        return !palavra.equals("sair");
    }

    private void colocarCartasAtaque(Jogador jogadorAtacante, ArrayList<String> listaCartasAtaque) {
        /*
        ver quais sao os monstros que foram escolhidos
         */
    }


    private void realizarCompra(Jogador jogador1, Jogador jogador2) {
        //jogador 1 e 2 compram uma carta
        boolean j1AindaPodePuxarCarta = verificarNumeroDeCartas(jogador1);
        boolean j2AindaPodePuxarCarta = verificarNumeroDeCartas(jogador2);
        if (j1AindaPodePuxarCarta) {
            puxarCarta(jogador1);
        } else {
            System.out.print("Jogador 1 com numero maximo de cartas em mão!\n");
        }
        if (j2AindaPodePuxarCarta) {
            puxarCarta(jogador2);
        } else {
            System.out.print("Jogador 2 com numero maximo de cartas em mão!\n");
        }
    }

    private boolean verificarNumeroDeCartas(Jogador jogador) {
        return obterNumeroCartasMao(jogador) < 10;
    }

    private int obterNumeroCartasMao(Jogador jogador) {
        return jogador.getMao().size();
    }

    private void puxarCarta(Jogador jogador) {
        jogador.puxarCarta();
    }

    private void permitirJogarCarta(Jogador jogador, Mesa mesa, MomentosDoTurno momentoDoTurno) {

        Carta cartaEscolhida = null;
        if (jogadorQuerJogarCarta() && jogadorPodeJogarCarta(jogador, mesa)) {
            //colocar uma exceção do jogador decidir nao jogar a carta
            boolean cartaErradaEscolhida = true;
            while (cartaErradaEscolhida) {
                try {
                    cartaEscolhida = perguntarCartaDesejada(jogador);
                    cartaErradaEscolhida = false;
                } catch (Exception NullPointerException) {
                    System.out.print("Você digitou um nome de carat invalido, tente novamente!\n");
                }
            }

            atualizarManaJogador(cartaEscolhida, jogador);
            colocarCartaEmCampo(jogador, cartaEscolhida);
            ArrayList<Efeito> listaEfeitos = listarEfeitos(cartaEscolhida);
            for (Efeito efeito : listaEfeitos) {
                if (permitirUsoEfeito(efeito, momentoDoTurno)) {
                    cartaEscolhida.ativarEfeito(efeito, mesa);
                }
            }
        }

    }


    private boolean permitirUsoEfeito(Efeito efeito, MomentosDoTurno momentoDoTurno) {
        return efeito.getMomentoQueSeraLido() == momentoDoTurno;

    }

    private boolean jogadorQuerJogarCarta() {
        System.out.print("Deseja jogar uma carta? (y/n) \n");
        Scanner scan = new Scanner(System.in);
        String resposta = scan.nextLine();
        scan.close();
        if (resposta.equals("y") || resposta.equals("Y")) {
            return true;
        } else if (resposta.equals("n") || resposta.equals("N")) {
            return false;
        } else {
            System.out.print("Escolha uma carta válida!\n");
            return jogadorQuerJogarCarta();
        }
    }

    private boolean jogadorPodeJogarCarta(Jogador jogador, Mesa mesa) {
        ArrayList<Carta> mao = jogador.getMao();
        int mana = jogador.getManaAtual();
        if (numeroCartasCampoExcedido(jogador)) {
            return false;
        }
        for (Carta carta : mao) {
            if (carta.getTipo() && carta.getCusto() <= (mana + jogador.getManaFeitico())) {
                return true;
            }
            if (carta.getCusto() <= mana) {
                return true;
            }
        }
        return false;
    }

    private boolean numeroCartasCampoExcedido(Jogador jogador) {

        return false;
    }

    private Carta perguntarCartaDesejada(Jogador jogador) {
        System.out.print("Qual carta você deseja usar?\n");
        String nomeCarta = obterNomeCarta();
        return acharCartaPeloNome(nomeCarta, jogador);
    }

    private String obterNomeCarta() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private Carta acharCartaPeloNome(String nomeCarta, Jogador jogador) {
        ArrayList<Carta> mao = jogador.getMao();
        for (Carta carta : mao) {
            if (carta.getNome().equals(nomeCarta)) {
                return carta;
            }
        }
        return null;
    }

    private void atualizarManaJogador(Carta cartaEscolhida, Jogador jogador) {
        int custoCarta = cartaEscolhida.getCusto();
        if (cartaEscolhida.getTipo() == true) {
            custoCarta = custoCarta - jogador.getManaFeitico();
            jogador.diminuirManaFeitico(jogador.getManaFeitico());
        }
        jogador.diminuirManaAtual(custoCarta);
    }

    private void colocarCartaEmCampo(Jogador jogador, Carta cartaEscolhida) {
        jogador.removerCartaMao(cartaEscolhida);
        jogador.adicionarCartaCampo(cartaEscolhida);

    }

    private ArrayList<Efeito> listarEfeitos(Carta cartaEscolhida) {
        return cartaEscolhida.getListaEfeitos();
    }

    private boolean jogadorAtacanteMorreu(Jogador jogadorAtacante){
        if (jogadorAtacante.getVida() <= 0){
            return true;
        }
        return false;
    }
    private boolean jogadorDefensorMorreu(Jogador jogadorDefensor){
        if(jogadorDefensor.getVida() <= 0){
            return true;
        }
        return false;
    }
}
