package com.unicamp.mc322.trabalho.jogo.expansao.carta;

import java.util.ArrayList;
import java.util.Collections;

public abstract class Carta {
    private String nomeCarta;
    private int custo;
    private ArrayList<Efeito> listaEfeitos = new ArrayList<>(); //uma carta pode ter mais de um feitiço
    protected Carta(String nomeCarta, int custo, Efeito... efeitos){
        this.nomeCarta = nomeCarta;
        this.custo = custo;
        Collections.addAll(listaEfeitos,efeitos);

    }

}
