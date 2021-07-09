package com.unicamp.mc322.trabalho.jogador;

import java.util.ArrayList;
import java.util.List;

public class DecksPadroes {
    private List<Deck> decksPadroes = new ArrayList<Deck>();

    public void addDeckPadrao(Deck novoDeck) {
        //Add um novo deck padrao ao jogo.
        decksPadroes.add(novoDeck);
    }

    public List<Deck> getDecksPadroes() {
        return decksPadroes;
    }
}
