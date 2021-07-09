
package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardManager {
    private final Mesa mesa;
    private final Jogador jogadorAtacante;
    private final Jogador jogadorDefensor;

    public BoardManager(Mesa mesa) {
        this.mesa = mesa;
        //primeiro jogador
        jogadorAtacante = mesa.getJogadores()[0];
        //segundo jogador
        jogadorDefensor = mesa.getJogadores()[1];
    }

    public boolean executarPassosJogo() {
        String resposta;
        realizarCompra(jogadorAtacante, jogadorDefensor);
        Scanner scan = new Scanner(System.in);
        //decidir quando acabara
        do {
            permitirJogarCarta(jogadorAtacante, mesa);
            if (jogadorDefensorMorreu(jogadorDefensor)) {
                return false;
            }

            permitirJogarCarta(jogadorDefensor, mesa);
            if (jogadorAtacanteMorreu(jogadorAtacante)) {
                return false;
            }
            System.out.print("Atacante,se desejar atacar escreva 'atacar'. Se desejar pular o ataque escreva 'pular'" +
                    "se deseja continuar a jogar digite qualquer letra");
            resposta = scan.nextLine();
        } while (!(resposta.equals("atacar") || resposta.equals("pular")));
        if (resposta.equals("atacar")) {
            //so vai escolher os monstros que vao atacar
            escolherMonstrosAtaque(jogadorAtacante);
            //so vai escolher os monstros que vao defender
            escolherMonstrosDefesa(jogadorDefensor);
            //permite que o jogador ataque com seus monstros e os defensores defendam ao mesmo tempo, verifica efeito dos dois
            permitirBatalha(jogadorAtacante, mesa);
            if (jogadorDefensorMorreu(jogadorDefensor)) {
                return false;
            }
            //ve se os monstros que restam tem algum efeito pos batalha e tira os monstros mortos de campo
            verificarMonstrosPosBatalha(jogadorAtacante, jogadorDefensor);
        }
        //inverter as posiçoes de quem é o atacante e quem é o defensor
        return executarPassosJogo();

    }

    private void verificarMonstrosPosBatalha(Jogador jogadorDefensor, Jogador jogadorAtacante) {
        verificarMonstros(jogadorAtacante);
        verificarMonstros(jogadorDefensor);
    }

    private void verificarMonstros(Jogador jogador) {
        ArrayList<Monstro> cartasBatalhando = jogador.getCartasBatalhando();
        for (Monstro monstro : cartasBatalhando) {
            //colocar condiçao de que se a vida do monstro for menor ou igual a 0 nao pode usar o efeito
            for (Efeito efeito : monstro.getListaEfeitos()) {
                podeUsarEfeito(efeito, MomentosDoTurno.APOS_BATALHA);
            }
        }
        retornarMonstrosCampo(jogador);
    }

    private void retornarMonstrosCampo(Jogador jogador) {
        ArrayList<Monstro> cartasBatalhando = jogador.getCartasBatalhando();
        for (Monstro monstro : cartasBatalhando) {
            if (monstro.getVidaAtual() > 0) {
                //se a vida do monstro que sobrou for maior que 0, ele ainda está vivo e pode retornar ao campo
                jogador.adicionaMonstroCampo(monstro);
            }
            jogador.removerCartaBatalha(monstro);
        }
    }

    private void permitirBatalha(Jogador jogadorAtacante, Mesa mesa) {
        ArrayList<Monstro> monstrosEmBatalha = jogadorAtacante.getCartasBatalhando();
        int numeroMonstrosBatalha = monstrosEmBatalha.size();
        for (int i = 0; i < numeroMonstrosBatalha; i++) {
            Monstro monstro = monstrosEmBatalha.get(i);
            for (Efeito efeito : monstro.getListaEfeitos()) {
                if (podeUsarEfeito(efeito, MomentosDoTurno.ANTES_BATALHA)) {
                    monstro.ativarEfeito(efeito, jogadorAtacante, mesa, monstro);
                }
            }
            Jogador jogadorDefensor = acharJogadorDefensor(jogadorAtacante, mesa);
            monstro.atacar(jogadorDefensor, i);
        }
    }

    private Jogador acharJogadorDefensor(Jogador jogadorAtacante, Mesa mesa) {
        Jogador jogadorDefensor;
        if (jogadorAtacante == mesa.getJogador1()) {
            jogadorDefensor = mesa.getJogador2();
        } else {
            jogadorDefensor = mesa.getJogador1();
        }
        return jogadorDefensor;
    }

    private void escolherMonstrosDefesa(Jogador jogadorDefensor) {
        //perguntar a ele quais cartas ele quer colocar pro ataque (em ordem)
        ArrayList<String> listaCartasDefesa = interagirComUsuarioDefensor(jogadorDefensor);
        //todas as cartas sao validas
        colocarCartasDefesa(jogadorDefensor, listaCartasDefesa);
    }

    private ArrayList<String> interagirComUsuarioDefensor(Jogador jogadorDefensor) {
        String nomeMonstro;
        ArrayList<String> listaCartasDefesa = inicializarArrayList(numeroCartasAtacando());
        mensagemParaUsuario();
        listarCartasCampo(jogadorDefensor);
        Scanner scan = new Scanner(System.in);
        do {
            nomeMonstro = scan.nextLine();
            int posicaoMonstro = scan.nextInt();
            if (naoEscreveuSair(nomeMonstro) && ehMonstroValido(jogadorDefensor, nomeMonstro)) {
                try {
                    //monstro adicionado na posição escolhida pelo usuario
                    listaCartasDefesa.add(posicaoMonstro, nomeMonstro);
                } catch (Exception IndexOutOfBoundsException) {
                    System.out.print("Escolha um numero dentro do intervalo solicitado!");
                }
            }
        } while (naoEscreveuSair(nomeMonstro));
        scan.close();
        return listaCartasDefesa;
    }

    private ArrayList<String> inicializarArrayList(int numeroCartasAtacando) {
        ArrayList<String> listaCartasDefesa = new ArrayList<>();
        //para cada carta no ataque, coloque um endereço null no array
        for (int i = 0; i < numeroCartasAtacando; i++) {
            listaCartasDefesa.add(null);
        }
        return listaCartasDefesa;
    }

    private void mensagemParaUsuario() {
        System.out.printf("Escolha as cartas que serão usadas para defender" +
                " e em seguida digite a posição desejada (0 a %d)." +
                " Digite 'sair' para finalizar\n", numeroCartasAtacando() - 1);
    }

    private int numeroCartasAtacando() {
        return jogadorAtacante.getCartasBatalhando().size();
    }

    private void colocarCartasDefesa(Jogador jogadorDefensor, ArrayList<String> listaCartasDefesa) {
        for (String nomeCarta : listaCartasDefesa) {
            //pode retornar null quando a carta nao eh reconhecida
            Carta cartaParaDefesa = converterNomeCarta(jogadorDefensor, nomeCarta);
            moverMonstro(jogadorDefensor, (Monstro) cartaParaDefesa);
        }
    }

    private void escolherMonstrosAtaque(Jogador jogadorAtacante) {
        //perguntar a ele quais cartas ele quer colocar pro ataque (em ordem)
        ArrayList<String> listaCartasAtaque = interagirComUsuarioAtacante(jogadorAtacante);
        //todas as cartas sao validas
        colocarCartasAtaque(jogadorAtacante, listaCartasAtaque);
    }

    private ArrayList<String> interagirComUsuarioAtacante(Jogador jogadorAtacante) {
        String nomeMonstro;
        ArrayList<String> listaCartasAtaque = new ArrayList<>();
        System.out.print("Escolha em ordem as cartas que você colocará em ataque. Digite 'sair' para finalizar\n");
        listarCartasCampo(jogadorAtacante);
        Scanner scan = new Scanner(System.in);
        do {
            nomeMonstro = scan.nextLine();
            if (naoEscreveuSair(nomeMonstro) && ehMonstroValido(jogadorAtacante, nomeMonstro)) {
                listaCartasAtaque.add(nomeMonstro);
            }
        } while (naoEscreveuSair(nomeMonstro));
        scan.close();
        return listaCartasAtaque;
    }

    private void listarCartasCampo(Jogador jogadorAtacante) {
        for (Carta carta : jogadorAtacante.getCartasEmCampo()) {
            System.out.printf("%s\n", carta.getNome());
        }
    }


    private boolean naoEscreveuSair(String palavra) {
        return !palavra.equals("sair");
    }

    private boolean ehMonstroValido(Jogador jogadorAtacante, String nomeMonstro) {
        if (jogadorAtacante.acharCartaCampoNome(nomeMonstro)) {
            return true;
        } else {
            System.out.print("Por favor, digite o nome de uma carta que esteja presente no seu campo!\n");
            return false;
        }

    }

    private void colocarCartasAtaque(Jogador jogadorAtacante, ArrayList<String> listaCartasAtaque) {

        for (String nomeCarta : listaCartasAtaque) {
            Carta cartaParaAtaque = converterNomeCarta(jogadorAtacante, nomeCarta);
            moverMonstro(jogadorAtacante, (Monstro) cartaParaAtaque);
        }

    }

    private Carta converterNomeCarta(Jogador jogador, String carta) {
        return jogador.converterNomeCarta(carta);
    }

    private void moverMonstro(Jogador jogador, Monstro monstro) {
        jogador.adicionarCartaBatalha(monstro);
        jogador.removerMonstroCampo(monstro);
    }

    private void realizarCompra(Jogador jogador1, Jogador jogador2) {
        //jogador 1 e 2 compram uma carta
        boolean j1AindaPodePuxarCarta = verificarNumeroDeCartasMao(jogador1);
        boolean j2AindaPodePuxarCarta = verificarNumeroDeCartasMao(jogador2);
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

    private boolean verificarNumeroDeCartasMao(Jogador jogador) {
        return obterNumeroCartasMao(jogador) < 10;
    }

    private int obterNumeroCartasMao(Jogador jogador) {
        return jogador.getMao().size();
    }

    private void puxarCarta(Jogador jogador) {
        jogador.puxarCarta();
    }

    private void permitirJogarCarta(Jogador jogador, Mesa mesa) {

        Carta cartaEscolhida = null;
        if (jogadorQuerJogarCarta() && jogadorPodeJogarCarta(jogador)) {
            //colocar uma exceção do jogador decidir nao jogar a carta
            boolean cartaErradaEscolhida = true;
            while (cartaErradaEscolhida) {
                try {
                    cartaEscolhida = perguntarCartaDesejada(jogador);
                    cartaErradaEscolhida = false;
                } catch (Exception NullPointerException) {
                    System.out.print("Você digitou um nome de carta invalido, tente novamente!\n");
                }
            }

            atualizarManaJogador(cartaEscolhida, jogador);
            if (ehMonstro(cartaEscolhida)) {
                colocarMonstroEmCampo(jogador, (Monstro) cartaEscolhida);
            }

            ArrayList<Efeito> listaEfeitos = listarEfeitos(cartaEscolhida);
            for (Efeito efeito : listaEfeitos) {
                if (podeUsarEfeito(efeito, MomentosDoTurno.APOS_INVOCACAO)) {
                    cartaEscolhida.ativarEfeito(efeito, jogador, mesa, cartaEscolhida);
                }
            }

        }

    }

    private boolean ehMonstro(Carta cartaEscolhida) {
        return !cartaEscolhida.ehFeitico();
    }

    private void colocarMonstroEmCampo(Jogador jogador, Monstro monstroEscolhido) {
        jogador.adicionaMonstroCampo(monstroEscolhido);
        jogador.removerCartaMao(monstroEscolhido);

    }

    private boolean podeUsarEfeito(Efeito efeito, MomentosDoTurno momentoDoTurno) {
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

    private boolean jogadorPodeJogarCarta(Jogador jogador) {
        ArrayList<Carta> mao = jogador.getMao();
        int mana = jogador.getManaAtual();
        if (numeroCartasCampoExcedido(jogador)) {
            return false;
        }
        for (Carta carta : mao) {
            if (carta.ehFeitico() && carta.getCusto() <= (mana + jogador.getManaFeitico())) {
                return true;
            }
            if (carta.getCusto() <= mana) {
                return true;
            }
        }
        return false;
    }

    private boolean numeroCartasCampoExcedido(Jogador jogador) {
        return jogador.getCartasEmCampo().size() < 6;
    }

    private Carta perguntarCartaDesejada(Jogador jogador) {
        System.out.print("Qual carta você deseja usar?\n");
        listarCartasMao(jogador);
        String nomeCarta = obterNomeCarta();
        return acharCartaPeloNome(nomeCarta, jogador);
    }

    private void listarCartasMao(Jogador jogador) {
        for (Carta carta : jogador.getMao()) {
            System.out.printf("%s\n", carta.getNome());
        }
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
        if (cartaEscolhida.ehFeitico()) {
            custoCarta = custoCarta - jogador.getManaFeitico();
            jogador.diminuirManaFeitico(jogador.getManaFeitico());
        }
        jogador.diminuirManaAtual(custoCarta);
    }

    private ArrayList<Efeito> listarEfeitos(Carta cartaEscolhida) {
        return cartaEscolhida.getListaEfeitos();
    }

    private boolean jogadorAtacanteMorreu(Jogador jogadorAtacante) {
        return jogadorAtacante.getVida() <= 0;
    }

    private boolean jogadorDefensorMorreu(Jogador jogadorDefensor) {
        return jogadorDefensor.getVida() <= 0;
    }
}
