package com.unicamp.mc322.trabalho.jogador;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Usuario {
    private String nick;
    private String id;
    private Map<String, Deck> decks = new HashMap<String, Deck>(); //dicionario com os decks salvos pelo usuario e o nome dado para o deck como key;
    private Deck mainDeck; //Deck principal apontado pelo jogador;

    public Usuario(String nome) {
        this.nick = nome;
    }

    private String obterTagRandom() {
        Random random = new Random();
        return String.format("%04d", random.nextInt(10000));
    }

    public void criarId() {
        this.id = nick + "#" + this.obterTagRandom();
    }

    public String getId() {
        //retorna o id do jogador(nome#DDDD) sendo DDDD 4 digitos ;
        return id;
    }

    public void imprimirId() {
        //METODO DE TESTE (***APAGAR DEPOIS)
        System.out.printf("%s", this.getId());
    }

    private void atribuirNomeUnico(String nome, Deck deck) {
        //Evita nomes repetidos;
        String novoNome = nome;
        if(decks.containsKey(nome)) {
            int i = 1;
            do {
                novoNome = nome;
                novoNome += " (" + i + ")";
                i++;
            } while(decks.containsKey(novoNome));
            deck.setNovoNome(novoNome);
        }

    }

    public void addNovoDeck(String nome, Deck novoDeck) {
        //add um novo deck;
        this.atribuirNomeUnico(nome, novoDeck);
        decks.put(nome, novoDeck);
        System.out.printf("Novo deck \"%s\" adcionado aos decks de %s", novoDeck.getNome(), this.id);

    }



    public Map<String, Deck> getDecks() {
        return decks;
    }

    public Deck getDeck(String nome) {
        return decks.get(nome);
    }

    public void deletarDeck(String nome) {
        //Deleta o deck associado ao jogador, dado o nome do deck;
        decks.remove(nome);
    }

    public void imprimirDecks() {
        for(Deck deck : decks.values()) {
            deck.imprimirDeck();
            System.out.println();
        }
    }

    public void setMainDeck(String nome) {
        //Seta um deck como default para o jogador;
        this.mainDeck = decks.get(nome);
    }


}
