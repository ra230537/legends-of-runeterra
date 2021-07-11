package com.unicamp.mc322.trabalho.jogador;

import java.util.*;

import com.unicamp.mc322.trabalho.jogo.GameException;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

public class Jogador {
    //na mï¿½o ï¿½ possivel ter no maximo 10 cartas, qualquer outra carta retirada do baralho sera descartada;
    protected Usuario usuario;
    private Deck deckEscolhido;
    private EstadoJogador estadoJogador;
    protected boolean bot = false;
    private int vida;
    private int mana;
    private int manaAtual;
    private int manaFeitico = 0;
    private ArrayList <Monstro> cartasBatalhando = new ArrayList<>();
    private ArrayList <Monstro> cartasEmCampo = new ArrayList<>();
    private ArrayList <Carta> mao = new ArrayList<>();

    public Jogador(Usuario usuario) {
        this.usuario = usuario;
        Map<String, Deck> listaDecks = obterListaDecks(usuario);
        vida = 20;
        mana = 1;
        manaAtual = 1;
        //o deck escolhido precisa ser uma copia da lista de decks para que nao haja alteraï¿½ï¿½o no modelo de deck
        deckEscolhido = perguntarDeckUsuario(listaDecks);
        //funcao que coloca as cartas na mao do jogador
        //funcao que permite o jogador escolher trocar as 4 cartas iniciais que vem em sua mao .
    }

    protected Deck perguntarDeckUsuario(Map<String, Deck> listaDecks) {
        System.out.print("Qual deck da lista abaixo você deseja usar "+ usuario.getId() +"?\n");
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

    public EstadoJogador getEstadoJogador() {
        return estadoJogador;
    }

    public void setEstadoJogador(EstadoJogador novoEstado) {
        estadoJogador = novoEstado;
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

    public void imprimirMao() {
        System.out.print("Mão de " + usuario.getId() + ":");
        for(Carta carta : mao){
            carta.imprimirCarta();
        }
        System.out.println();
    }

    public void puxarCarta(){
        //pega a carta de cima do deck padrao utilizado
        Carta cartaPuxada = deckEscolhido.tirarCartaTopo();
        mao.add(cartaPuxada);
    }

    public void trocarCarta(Carta cartaASerTrocada) {
        if(mao.contains(cartaASerTrocada)) {
            mao.set(mao.indexOf(cartaASerTrocada), deckEscolhido.tirarCartaTopo());
        }
        else {
            throw new GameException("Essa carta não está na mão.");
        }

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

    public void adicionaMonstroCampo(Monstro monstro){
        //ajeitar o campo colocar um array de 4 espaï¿½os de listas
        cartasEmCampo.add(monstro);
    }
    public void removerMonstroCampo(Monstro monstro){
        cartasEmCampo.remove(monstro);
    }
    public void adicionarCartaAtaque(Monstro monstro){
        cartasBatalhando.add(monstro);
    }
    public void adicionarCartaDefesa(int posicao,Monstro monstro){
        cartasBatalhando.set(posicao,monstro);
    }
    public void removerCartaBatalha(Monstro monstro){
        cartasBatalhando.remove(monstro);
    }
    public ArrayList<Monstro> getCartasEmCampo() {
        return cartasEmCampo;
    }

    public void removerCartaMao(Carta carta){
        mao.remove(carta);
    }

    public ArrayList<Monstro> getCartasBatalhando() {
        return cartasBatalhando;
    }

    public boolean acharCartaCampoNome (String nome){
        for(Carta carta: cartasEmCampo){
            if (carta.getNome().equals(nome)){
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param nome
     * @return null quando o nome da carta não é encontrado e o objeto carta quando esse possui
     * o nome informado no parametro
     */
    public Carta converterNomeCartaCampo (String nome){
        for(Carta carta: cartasEmCampo){
            if (carta.getNome().equals(nome)){
                return carta;
            }
        }
        return null;
    }
    public Carta converterNomeCartaMao (String nome){
        for(Carta carta: mao){
            if (carta.getNome().equals(nome)){
                return carta;
            }
        }
        return null;
    }
    public Carta converterNomeCartaBatalha (String nome){
        for(Carta carta: cartasBatalhando){
            if (carta.getNome().equals(nome)){
                return carta;
            }
        }
        return null;
    }
    public void DiminuirVida(int valor){
        vida = vida - valor;

    }

    public boolean ehBot() {
        return bot;
    }

    public void addCartaMao(Carta carta){
        mao.add(carta);
    }

    public void inicializarListaCartasDefesa(int numeroCartasAtacando){
        cartasBatalhando.clear();
        for (int i = 0; i < numeroCartasAtacando; i++) {
            cartasBatalhando.add(null);
        }
    }
    public int getCartaMaiorCustoCampo(){
        int max = 0;
        for(Monstro monstro: cartasEmCampo){
            if (monstro.getCusto() > max){
                max = monstro.getCusto();
            }
        }
        return max;
    }
    public int getNumeroCartasCampo(){
        return cartasEmCampo.size();
    }
    public void substituirMonstroCampo(int indice,Monstro monstro){
        cartasEmCampo.set(indice,monstro);
    }
    public void embaralharDeck(){
        deckEscolhido.embaralharCartas();
    }
    public void aumentarUmDeMana(){
        manaFeitico+=manaAtual;
        if(manaFeitico>3){
            manaFeitico=3;
        }

        if(mana<10){
            mana = mana + 9;
            manaAtual = mana;
        }
    }
    public boolean campoVazio(){
        return cartasEmCampo.size() == 0;
    }
    public boolean campoBatalhaVazio(){
        return cartasBatalhando.size() == 0;
    }
    public void limparCampoBatalha(){
        cartasBatalhando.clear();
    }
}
