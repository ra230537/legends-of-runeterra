package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Jogador;

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
            System.out.print("Jogador 2 com numero maximo de cartas em mão1\n");
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
}
