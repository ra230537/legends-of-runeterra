package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardManager {
    Mesa mesa;
    public BoardManager(Mesa mesa){
        this.mesa = mesa;
    }

    public boolean executarPassosJogo(Jogador jogadorAtacante,Jogador jogadorDefensor, Mesa mesa){
        realizarCompra(jogadorAtacante,jogadorDefensor);
        /*		1) jogador 1 e 2 puxam uma carta do deck

         2)jogador 1 decide jogar uma carta
         -se a carta for jogada o board manager ve se a carta tem o efeito possivel de ser utlizado, ou seja um efeito que é habilitado assim que a carta eh evocada

         3)jogador 2 decide jogar uma carta
         -leitura da carta lançada pelo board manager
         //esse procedimento se repete até o momento que o jogador 1 (atacante) resolver fazer seu ataque

         4)jogador 1 escolhe quais monstros irão atacar
         -para cada monstro que o jogador 1 coloca no ataque o board manager verifica se ele possui algum efeito que possa ser usado antes do ataque (ex: atacar nexus),
         caso o monstro possua, o board manager vai se encarregar de fazer o monstro executar seu efeito de alguma forma

         5)jogador 2 escolhe quais monstros vão defender
         - para cada monstro que o jogador 2... obs: ainda não há efeito de monstro que funcione no momento da defesa, mas nao quer dizer que não possa existir

         6)cada carta do jogador 1 ataca enquanto os do jogador 2 defendem
         -board manager verifica efeito tanto de pos ataque, no caso do jogador 1 quanto de pos defesa, no caso do jogador 2

         7)fim da rodada
         - o board manager le novamente todas as cartas para ver se alguma possui efeito que é ativado apos uma rodada de ataque/defesa.

         8)rodada é finalizada as posições de ataque e defesa se invertem.*/

    }
    private void realizarCompra(Jogador jogador1,Jogador jogador2){
        //jogador 1 e 2 compram uma carta
        boolean j1AindaPodePuxarCarta = verificarNumeroDeCartas(jogador1);
        boolean j2AindaPodePuxarCarta = verificarNumeroDeCartas(jogador2);
        if (j1AindaPodePuxarCarta){
            puxarCarta(jogador1);
        }else{
            System.out.print("Jogador 1 com numero maximo de cartas em mão!\n");
        }
        if (j2AindaPodePuxarCarta){
            puxarCarta(jogador2);
        }else{
            System.out.print("Jogador 2 com numero maximo de cartas em mão!\n");
        }
    }

    private boolean verificarNumeroDeCartas(Jogador jogador){
        return obterNumeroCartasMao(jogador) < 10;
    }
    private int obterNumeroCartasMao(Jogador jogador){
        return jogador.getMao().size();
    }
    private void puxarCarta(Jogador jogador){
        jogador.puxarCarta();
    }

    private void permitirJogarCarta(Jogador jogador,Mesa mesa){
        /*
        pergunta ao jogador se ele quer jogar uma carta
        se sim pergunta a ele qual o numero do indice da carta que ele quer jogar (pode perguntar
        o nome tambem porque a carta foi implementada usando um ArrayList entao nao tem problema
        eu acho que se pah ainda tem um dicionario entao se pah que vai ser mais facil ainda escolher
        qual a carta basta pegar a string que o usuario digitar, converter ela no endereço da carta e pegar
        o endereço
        dai, se o usuario escolher uma carta valida
        verifica se tem energia pra usar, tira da mao dele, atualiza a energia atual do jogador
        , coloca no jogo e ve se tem algum efeito que pode ser usado no momento
        se ele escolher uma carta invalida manda ele escolher dnv pq nao tem carta valida

         */
        Carta cartaEscolhida = null;
        if(jogadorQuerJogarCarta() && jogadorPodeJogarCarta(jogador)){
            //colocar uma exceção do jogador decidir nao jogar a carta
            boolean cartaErradaEscolhida = true;
            while(cartaErradaEscolhida){
                try{
                    cartaEscolhida = perguntarCartaDesejada(jogador);
                    cartaErradaEscolhida = false;
                }catch (Exception NullPointerException){
                    System.out.print("Você digitou um nome de carat invalido, tente novamente!\n");
                }
            }

            atualizarManaJogador(cartaEscolhida,jogador);
            colocarCartaEmCampo(jogador);
            ArrayList<Efeito> listaEfeitos = listarEfeitos(jogador);
            for(Efeito efeito: listaEfeitos){
                if(permitirUsoEfeito(efeito)){
                    cartaEscolhida.ativarEfeito(efeito,mesa);
                }
            }
        }

    }
    private boolean verificarUsoEfeito(Efeito efeito, MomentosDoTurno momentoDoTurno){
        return efeito.getMomentoQueSeraLido() == momentoDoTurno;

    }
    private boolean jogadorQuerJogarCarta(){
        System.out.print("Deseja jogar uma carta? (y/n) \n");
        Scanner scan = new Scanner(System.in);
        String resposta = scan.nextLine();
        if(resposta.equals("y") || resposta.equals("Y") ){
            return true;
        }else if(resposta.equals("n") || resposta.equals("N")){
            return false;
        }else{
            System.out.print("Escolha uma carta válida!\n");
            return jogadorQuerJogarCarta();
        }
    }
    private boolean jogadorPodeJogarCarta(Jogador jogador){
        ArrayList<Carta> mao = jogador.getMao();
        int mana = jogador.getMana();
        for(Carta carta : mao){
            if (carta.getCusto() <= jogador.getManaAtual()){
                return true;
            }
        }
        return false;
    }
    private Carta perguntarCartaDesejada(Jogador jogador){
        System.out.print("Qual carta você deseja usar?\n");
        String nomeCarta = obterNomeCarta();
        return acharCartaPeloNome(nomeCarta,jogador);
    }
    private String obterNomeCarta(){
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }
    private Carta acharCartaPeloNome(String nomeCarta,Jogador jogador){
        ArrayList <Carta> mao = jogador.getMao();
        for(Carta carta : mao){
            if (carta.getNome().equals(nomeCarta)){
                return carta;
            }
        }
        return null;
    }

    private void atualizarManaJogador(Carta cartaEscolhida,Jogador jogador){
        int custoCarta = cartaEscolhida.getCusto();
        jogador.diminuirManaAtual(custoCarta);
    }
    private void colocarCartaEmCampo(Jogador jogador,Carta cartaEscolhida){
        ArrayList <Carta> mao = jogador.getMao();
        jogador.
    }
}
