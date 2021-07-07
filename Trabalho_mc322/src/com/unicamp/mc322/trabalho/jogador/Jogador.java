package com.unicamp.mc322.trabalho.jogador;

import java.util.*;

import com.unicamp.mc322.trabalho.jogo.Jogo;
import com.unicamp.mc322.trabalho.jogo.Mesa;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;

public class Jogador {
    private Usuario usuario;
    private Deck deckEscolhido;
    private int vida;
    private int mana;
    private int manaAtual;
    private int manaFeitico = 0;
    private ArrayList <Carta> cartasEmCampo = new ArrayList<>();
    //na mão é possivel ter no maximo 10 cartas, qualquer outra carta retirada do baralho sera descartada;
    private ArrayList <Carta> mao = new ArrayList<>();
    public Jogador(Usuario usuario) {
        this.usuario = usuario;
        Map<String, Deck> listaDecks = obterListaDecks(usuario);
        vida = 20;
        mana = 1;
        manaAtual = 1;
        //o deck escolhido precisa ser uma copia da lista de decks para que nao haja alteração no modelo de deck
        deckEscolhido = perguntarDeckUsuario(listaDecks);
        //função que coloca as cartas na mao do jogador
        //função que permite o jogador escolher trocar as 4 cartas iniciais que vem em sua mao
    }

    private Deck perguntarDeckUsuario(Map<String, Deck> listaDecks) {
        System.out.print("Qual deck da lista abaixo você deseja usar?\n");
        Set<String> nomeDecks = obterSetNomes(listaDecks);

        for (String deck : nomeDecks) {
            System.out.printf("%s\n", deck);
        }
        String respostaUsuario = obterResposta();
        return obterDeckEscolhido(respostaUsuario, listaDecks);
    }

    private Map<String, Deck> obterListaDecks(Usuario usuario) {
        return usuario.getDecks();
    }

    private Set<String> obterSetNomes(Map<String, Deck> listaDecks) {
        return listaDecks.keySet();
    }

    private String obterResposta() {
        Scanner scan = new Scanner(System.in);
        return scan.nextLine();
    }

    private Deck obterDeckEscolhido(String nomeDeck, Map<String, Deck> listaDecks) {
        return listaDecks.get(nomeDeck);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public int getMana() {
        return mana;
    }

    public int getManaFeitico() {
        return manaFeitico;
    }

    public int getVida() {
        return vida;
    }

    public ArrayList<Carta> getMao() {
        return mao;
    }

    public void puxarCarta(){
        //pega a carta de cima do deck padrao utilizado
        Carta cartaPuxada = deckEscolhido.tirarCartaTopo();
        mao.add(cartaPuxada);
    }

    public int getManaAtual() {
        return manaAtual;
    }

    public Deck getDeckEscolhido() {
        return deckEscolhido;
    }

    public void diminuirManaAtual(int valor){
        manaAtual-=valor;
    }

    public void diminuirManaFeitico(int valor){
        manaFeitico-=valor;
    }

    public void adicionarCartaCampo(Carta carta){
        //ajeitar o campo colocar um array de 4 espaços de listas
        cartasEmCampo.add(carta);
    }

    public ArrayList<Carta> getCartasEmCampo() {
        return cartasEmCampo;
    }
    public void removerCartaMao(Carta carta){
        mao.remove(carta);
    }
}
