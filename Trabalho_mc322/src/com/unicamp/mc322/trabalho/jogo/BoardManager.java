package com.unicamp.mc322.trabalho.jogo;

import com.unicamp.mc322.trabalho.jogador.Bot;
import com.unicamp.mc322.trabalho.jogador.Jogador;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Carta;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Efeito;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.MomentosDoTurno;
import com.unicamp.mc322.trabalho.jogo.expansao.carta.Monstro;

import java.util.ArrayList;
import java.util.Scanner;

public class BoardManager {
    private final Mesa mesa;
    private final Scanner scanString;
    private final Scanner scanNumero;
    public BoardManager(Mesa mesa) {
        this.mesa = mesa;
        this.scanString = new Scanner(System.in);
        this.scanNumero = new Scanner(System.in);
    }


    public boolean executarPassosJogo(Jogador jogadorAtacante, Jogador jogadorDefensor) {
        int resposta;

        realizarCompra(jogadorAtacante, jogadorDefensor);

        if (!jogadorAtacante.ehBot()){
            vizualizarCartaDetalhadamente(jogadorAtacante);
        }
        if(!jogadorDefensor.ehBot()){
            vizualizarCartaDetalhadamente(jogadorDefensor);
        }
        do {
            turnoDoAtacante(jogadorAtacante);
            if (jogadorDefensorMorreu(jogadorDefensor)) {
                return true;
            }
            mesa.imprimirMesa();
            turnoDoDefensor(jogadorDefensor);
            if (jogadorAtacanteMorreu(jogadorAtacante)) {
                return true;
            }
            mesa.imprimirMesa();
            resposta = respostaFimTurno(jogadorAtacante);
            //o jogador escolhe se quer continuar jogando cartas ou se quer atacar
        } while (jogadorNaoQuerEncerrarTurno(resposta));

        if (jogadorPediuParaAtacar(resposta)) {

            turnoDeBatalha(jogadorAtacante, jogadorDefensor);
            if (jogadorDefensorMorreu(jogadorDefensor)) {
                return true;
            }
            //verificarMonstrosPosBatalha(jogadorAtacante, jogadorDefensor);
            verificarMonstrosPosBatalha(jogadorAtacante);
            verificarMonstrosPosBatalha(jogadorDefensor);
            mesa.imprimirMesa();
        }
        //fecharScanner(scan);
        jogadorAtacante.aumentarUmDeMana();
        jogadorDefensor.aumentarUmDeMana();
        verificarMonstrosFimDeTurno(jogadorAtacante);
        verificarMonstrosFimDeTurno(jogadorDefensor);
        return false;
    }



    private int respostaFimTurno(Jogador jogadorAtacante) {
        int resposta;
        if(jogadorAtacante.ehBot()){
            resposta = lerRespostaBot((Bot) jogadorAtacante);
        }else{
            perguntarAtacarPularJogarCarta();
            resposta = lerProximoNumero();
        }
        return resposta;
    }


    private int lerProximoNumero() {
        return scanNumero.nextInt();
    }


    private String lerProximaLinha() {
        return scanString.nextLine();
    }

    private void realizarCompra(Jogador jogador1, Jogador jogador2) {
        //jogador 1 e 2 compram uma carta
        boolean j1AindaPodePuxarCarta = verificarNumeroDeCartasMao(jogador1);
        boolean j2AindaPodePuxarCarta = verificarNumeroDeCartasMao(jogador2);
        if (j1AindaPodePuxarCarta) {
            puxarCarta(jogador1);
        } else {
            System.out.println(jogador1.getUsuario().getId() + " esta com o numero maximo de cartas na mão!");
        }
        if (j2AindaPodePuxarCarta) {
            puxarCarta(jogador2);
        } else {
            System.out.println(jogador2.getUsuario().getId() + " esta com o numero maximo de cartas na mão!");
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

    private void vizualizarCartaDetalhadamente(Jogador jogador) {
        //Scanner scan = criarScanner();
        String resposta = perguntarSeQuerVizualizar(jogador);
        if(resposta.equals("y")){
            String nomeCarta = PerguntarQualCartaQuerVizualizar(jogador);
            Carta carta = converterNomeCartaMao(jogador,nomeCarta);
            carta.imprimirCartaDetalhada();
        }
    }
    private String perguntarSeQuerVizualizar(Jogador jogador){
        System.out.printf("voce é o %s.Deseja ver alguma carta detalhadamente, %s?(y/n) \n",jogador.getEstadoJogador().toString(),jogador.getUsuario().getId());
        return lerProximaLinha();
    }
    private String PerguntarQualCartaQuerVizualizar(Jogador jogador) {
        System.out.print("Qual carta gostaria de vizualizar\n");
        listarCartasMao(jogador);
        return lerProximaLinha();
    }

    private void turnoDoAtacante(Jogador jogadorAtacante) {
        if (jogadorAtacante.ehBot()) {
            permitirJogarCartaBot((Bot) jogadorAtacante);
        } else {
            permitirJogarCarta(jogadorAtacante);
        }
    }

    private void turnoDoDefensor(Jogador jogadorDefensor) {
        if (jogadorDefensor.ehBot()) {
            permitirJogarCartaBot((Bot) jogadorDefensor);
        } else {
            permitirJogarCarta(jogadorDefensor);
        }
    }

    private void permitirJogarCarta(Jogador jogador) {

        Carta cartaEscolhida;
        int indiceSubstituicao = 0;
        if (jogadorQuerJogarCarta(jogador) && jogadorPodeJogarCarta(jogador)) {
            //eu tenho certeza que pelo menos uma carta pode ser usada em campo
            //seja substituindo uma carta por uma dentro da mao ou jogando uma carta normalmente
            cartaEscolhida = perguntarCartaDesejada(jogador);
            if (numeroCartasCampoExcedido(jogador)){
                indiceSubstituicao = perguntarJogadorQualIndiceSubstituir(jogador);
                atualizarManaJogadorCampoCheio(cartaEscolhida,jogador,indiceSubstituicao);
            }else{

                atualizarManaJogador(cartaEscolhida, jogador);//atualizar a mana com base se o campo tava cheio ou nao
            }
            if (ehMonstro(cartaEscolhida)) {
                if (numeroCartasCampoExcedido(jogador)){
                    substituirMonstroEmCampo(jogador,(Monstro) cartaEscolhida,indiceSubstituicao);
                }else{
                    colocarMonstroEmCampo(jogador, (Monstro) cartaEscolhida); //escolher uma posição pra colocar a carta caso o campo esteja cheio
                }

            }
            ArrayList<Efeito> listaEfeitos = listarEfeitos(cartaEscolhida);
            for (Efeito efeito : listaEfeitos) {
                if (podeUsarEfeito(efeito, MomentosDoTurno.APOS_INVOCACAO)) {
                    cartaEscolhida.ativarEfeito(efeito, jogador, mesa, cartaEscolhida);
                }
            }
        }
    }

    private void substituirMonstroEmCampo(Jogador jogador, Monstro monstroEscolhido, int indiceSubstituicao) {
        jogador.substituirMonstroCampo(indiceSubstituicao,monstroEscolhido);
        jogador.removerCartaMao(monstroEscolhido);
    }

    private boolean jogadorQuerJogarCarta(Jogador jogador) {
        System.out.printf("Voce e o %s. Deseja jogar uma carta, %s? (y/n) \n",jogador.getEstadoJogador().toString(),jogador.getUsuario().getId());
        //Scanner scan = criarScanner();
        String resposta = lerProximaLinha();
        System.out.printf("**************** A RESPOSTA DADA FOI %s ***************\n",resposta);
        //fecharScanner(scan);
        if (resposta.equals("y") || resposta.equals("Y")) {
            return true;
        } else if (resposta.equals("n") || resposta.equals("N")) {
            return false;
        } else {
            System.out.print("Escolha uma resposta válida!\n");
            return jogadorQuerJogarCarta(jogador);
        }
    }

    private boolean jogadorPodeJogarCarta(Jogador jogador) {
        ArrayList<Carta> mao = jogador.getMao();
        int mana = jogador.getManaAtual();

        for (Carta carta : mao) {
            if (carta.ehFeitico() && carta.getCusto() <= (mana + jogador.getManaFeitico())) {
                return true;
            }
            if (carta.getCusto() <= mana) {
                return true;
            }
            if(numeroCartasCampoExcedido(jogador)){
                if (!carta.ehFeitico() && carta.getCusto() <= (mana + jogador.getCartaMaiorCustoCampo())){
                    return true;
                }
            }
        }
        return false;
    }

    private boolean numeroCartasCampoExcedido(Jogador jogador) {
        return jogador.getCartasEmCampo().size() >= 6;
    }

    private Carta perguntarCartaDesejada(Jogador jogador) {
        int custo;
        int manaAtual = jogador.getManaAtual();
        int manaFeitico = jogador.getManaFeitico();
        System.out.printf("Qual carta você deseja usar? Você possui %d de mana\n",manaAtual);
        listarCartasMao(jogador);
        String nomeCarta = obterNomeCarta();
        Carta cartaEscolhida = acharCartaPeloNome(nomeCarta, jogador);
        if (cartaEscolhida != null) {
            custo = cartaEscolhida.getCusto();
        }else{
            System.out.print("Você escreveu um nome invalido. Tente novamente!\n");
            return perguntarCartaDesejada(jogador);
        }
        if (numeroCartasCampoExcedido(jogador)){
            if((custo > manaAtual + manaFeitico && cartaEscolhida.ehFeitico())
                || ((custo > manaAtual + cartaEscolhida.getCusto() && !cartaEscolhida.ehFeitico()))){
                System.out.print("Voce nao tem mana para jogar essa carta. Tente novamente!\n");
                return perguntarCartaDesejada(jogador);
            }
        }else{
            if ((custo > manaAtual && !cartaEscolhida.ehFeitico())
                    || (custo > manaAtual + manaFeitico && cartaEscolhida.ehFeitico())) {
                System.out.print("Voce nao tem mana para jogar essa carta. Tente novamente!\n");
                return perguntarCartaDesejada(jogador);
            }
        }
        return cartaEscolhida;
    }

    private void listarCartasMao(Jogador jogador) {
        for (Carta carta : jogador.getMao()) {
            System.out.printf("|%s (%d)|\n", carta.getNome(),carta.getCusto());
        }
    }

    private String obterNomeCarta() {
        return lerProximaLinha();
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
        int custoMonstroMao = cartaEscolhida.getCusto();
        if (cartaEscolhida.ehFeitico()) {
            custoMonstroMao = custoMonstroMao - jogador.getManaFeitico();
            jogador.diminuirManaFeitico(jogador.getManaFeitico());
        }
        jogador.diminuirManaAtual(custoMonstroMao);
    }

    private void atualizarManaJogadorCampoCheio(Carta cartaEscolhida, Jogador jogador,int respostaJogador) {
        int custoMonstroMao = cartaEscolhida.getCusto();
        if(numeroCartasCampoExcedido(jogador) && !cartaEscolhida.ehFeitico()){
            //fazer o jogador me dizer qual eh a carta que ele quer substituir
            Monstro monstroEscolhidoCampo = jogador.getCartasEmCampo().get(respostaJogador);
            int custoMonstroCampo = monstroEscolhidoCampo.getCusto();
            if(custoMonstroCampo < custoMonstroMao){
                jogador.diminuirManaAtual(custoMonstroMao-custoMonstroCampo);
                return ;
            }
        }
        if (cartaEscolhida.ehFeitico()) {
            custoMonstroMao = custoMonstroMao - jogador.getManaFeitico();
            jogador.diminuirManaFeitico(jogador.getManaFeitico());
        }
        jogador.diminuirManaAtual(custoMonstroMao);
    }

    private int perguntarJogadorQualIndiceSubstituir(Jogador jogador){
        System.out.printf("digite um numero de 1 a %d para indicar qual carta sera substituida",jogador.getNumeroCartasCampo());
        int respostaJogador = lerProximoNumero();
        if (respostaJogador > jogador.getNumeroCartasCampo() || respostaJogador < 1){
            System.out.print("Você digitou um número inválido!\n");
            return perguntarJogadorQualIndiceSubstituir(jogador);
        }else{
            return respostaJogador - 1;
        }
    }
    private boolean ehMonstro(Carta cartaEscolhida) {
        return !cartaEscolhida.ehFeitico();
    }

    private void colocarMonstroEmCampo(Jogador jogador, Monstro monstroEscolhido) {
        jogador.adicionaMonstroCampo(monstroEscolhido);
        jogador.removerCartaMao(monstroEscolhido);

    }

    private ArrayList<Efeito> listarEfeitos(Carta cartaEscolhida) {
        return cartaEscolhida.getListaEfeitos();
    }

    private boolean podeUsarEfeito(Efeito efeito, MomentosDoTurno momentoDoTurno) {
        return efeito.getMomentoQueSeraLido() == momentoDoTurno;

    }

    private boolean jogadorAtacanteMorreu(Jogador jogadorAtacante) {
        return jogadorAtacante.getVida() <= 0;
    }


    private boolean jogadorDefensorMorreu(Jogador jogadorDefensor) {
        return jogadorDefensor.getVida() <= 0;
    }

    private void permitirJogarCartaBot(Bot bot) {

        Carta cartaEscolhida ;

        if (botQuerJogarCarta(bot) && jogadorPodeJogarCarta(bot)) {
            //colocar uma exceção do jogador decidir nao jogar a carta
            cartaEscolhida = perguntarCartaDesejadaBot(bot);
            int indiceMonstroSubstituido = perguntarBotQualIndiceSubstituir(bot);
            if (numeroCartasCampoExcedido(bot)){
                atualizarManaJogadorCampoCheio(cartaEscolhida,bot,indiceMonstroSubstituido);
            }else{
                atualizarManaJogador(cartaEscolhida, bot);
            }

            if (ehMonstro(cartaEscolhida)) {
                if(numeroCartasCampoExcedido(bot)){
                    substituirMonstroEmCampo(bot,(Monstro) cartaEscolhida,indiceMonstroSubstituido);
                }else{
                    colocarMonstroEmCampo(bot, (Monstro) cartaEscolhida);
                }
            }

            ArrayList<Efeito> listaEfeitos = listarEfeitos(cartaEscolhida);
            for (Efeito efeito : listaEfeitos) {
                if (podeUsarEfeito(efeito, MomentosDoTurno.APOS_INVOCACAO)) {
                    cartaEscolhida.ativarEfeito(efeito, bot, mesa, cartaEscolhida);
                }
            }

        }

    }
    private int perguntarBotQualIndiceSubstituir(Bot bot){
        return bot.getNumeroRandom(6);
    }
    private boolean botQuerJogarCarta(Bot bot) {

        int resposta = bot.getNumeroRandom(2) ;
        return resposta == 1;
    }

    private Carta perguntarCartaDesejadaBot(Bot bot) {
        ArrayList<Carta> mao = bot.getMao();
        int indiceCarta;
        int manaAtual = bot.getManaAtual();
        int manaFeitico = bot.getManaFeitico();
        int custoCarta;
        Carta carta;
        int numeroCartasMao = mao.size();
        do {
            indiceCarta = bot.getNumeroRandom(numeroCartasMao);
            carta = mao.get(indiceCarta);
            custoCarta = carta.getCusto();
        } while ((custoCarta > manaAtual && !carta.ehFeitico()) ||
                (custoCarta > manaAtual + manaFeitico && carta.ehFeitico()));
        return carta;
    }

    private int lerRespostaBot(Bot bot) {
        return bot.getNumeroRandom(3)+1;
    }

    private void perguntarAtacarPularJogarCarta() {
        System.out.print("Atacante,se deseja acabar a rodada sem atacar digite '1'\nSe deseja atacar digite '2'\nSe deseja continuar a colocar cartas em campo digite outro numero.\n");


    }

    private boolean jogadorNaoQuerEncerrarTurno(int resposta) {
        return resposta != 1 && resposta != 2;
    }

    private boolean jogadorPediuParaAtacar(int resposta) {
        return resposta == 2;
    }

    private void turnoDeBatalha(Jogador jogadorAtacante, Jogador jogadorDefensor) {
        mesa.imprimirMesa();
        if (jogadorAtacante.ehBot()) {

            escolherMonstrosAtaqueBot((Bot) jogadorAtacante);
        } else {
            escolherMonstrosAtaque(jogadorAtacante);
        }
        mesa.imprimirMesa();
        if (jogadorDefensor.ehBot()) {
            escolherMonstrosDefesaBot((Bot) jogadorDefensor, jogadorAtacante);
        } else {
            escolherMonstrosDefesa(jogadorDefensor, jogadorAtacante);
        }
        mesa.imprimirMesa();
        permitirBatalha(jogadorAtacante);

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
        do {
            nomeMonstro = lerProximaLinha();
            if (naoEscreveuSair(nomeMonstro) && ehMonstroValido(jogadorAtacante, nomeMonstro)) {
                listaCartasAtaque.add(nomeMonstro);
            }
        } while (naoEscreveuSair(nomeMonstro));
        return listaCartasAtaque;
    }

    private void listarCartasCampo(Jogador jogadorAtacante) {
        for (Carta carta : jogadorAtacante.getCartasEmCampo()) {
            System.out.printf("%s\n", carta.getNome());
        }
    }

    private boolean ehMonstroValido(Jogador jogadorAtacante, String nomeMonstro) {
        if (jogadorAtacante.acharCartaCampoNome(nomeMonstro)) {
            return true;
        } else {
            System.out.print("Por favor, digite o nome de uma carta que esteja presente no seu campo!\n");
            return false;
        }

    }

    private boolean naoEscreveuSair(String palavra) {
        return !palavra.equals("sair");
    }

    private boolean escreveuSair(String palavra){
        return palavra.equals("sair");
    }
    private void colocarCartasAtaque(Jogador jogadorAtacante, ArrayList<String> listaCartasAtaque) {

        for (String nomeCarta : listaCartasAtaque) {
            Carta cartaParaAtaque = converterNomeCartaCampo(jogadorAtacante, nomeCarta);
            moverMonstro(jogadorAtacante, (Monstro) cartaParaAtaque);
        }

    }

    private Carta converterNomeCartaCampo(Jogador jogador, String carta) {
        return jogador.converterNomeCartaCampo(carta);
    }
    private Carta converterNomeCartaMao(Jogador jogador, String carta) {
        return jogador.converterNomeCartaMao(carta);
    }
    private void moverMonstro(Jogador jogador, Monstro monstro) {
        jogador.adicionarCartaAtaque(monstro);
        jogador.removerMonstroCampo(monstro);
    }

    private void escolherMonstrosAtaqueBot(Bot botAtacante) {
        //perguntar a ele quais cartas ele quer colocar pro ataque (em ordem)
        int numeroCartasEscolhidas = interagirComBotAtacante(botAtacante);
        //todas as cartas sao validas
        colocarCartasAtaqueBot(botAtacante, numeroCartasEscolhidas);
    }

    private int interagirComBotAtacante(Bot bot) {
        int numeroCartasCampo = bot.getCartasEmCampo().size();
        return bot.getNumeroRandom(numeroCartasCampo + 1);

    }

    private void colocarCartasAtaqueBot(Bot botAtacante, int numeroCartasEscolhidas) {
        ArrayList<Integer> indicesCartasUsadas = new ArrayList<>();
        for (int i = 0; i < numeroCartasEscolhidas; i++) {
            int numeroEscolhido;
            do {
                numeroEscolhido = botAtacante.getNumeroRandom(numeroCartasEscolhidas);
            } while (indicesCartasUsadas.contains(numeroEscolhido));
            indicesCartasUsadas.add(numeroEscolhido);
            Monstro monstro = botAtacante.getCartasEmCampo().get(numeroEscolhido);
            botAtacante.adicionarCartaAtaque(monstro);
        }

    }

    private void escolherMonstrosDefesa(Jogador jogadorDefensor, Jogador jogadorAtacante) {
        //perguntar a ele quais cartas ele quer colocar pro ataque (em ordem)
        ArrayList<String> listaCartasDefesa = interagirComUsuarioDefensor(jogadorDefensor, jogadorAtacante);
        //todas as cartas sao validas
        colocarCartasDefesa(jogadorDefensor, listaCartasDefesa);
    }

    private ArrayList<String> interagirComUsuarioDefensor(Jogador jogadorDefensor, Jogador jogadorAtacante) {
        String nomeMonstro;
        ArrayList<String> listaCartasDefesa = inicializarArrayList(numeroCartasAtacando(jogadorAtacante));
        //ou nao tem monstros pra defender ou monstros inimigos pra atacar
        if(jogadorDefensor.campoVazio() || jogadorAtacante.campoBatalhaVazio()){
            return listaCartasDefesa;
        }
        mensagemParaUsuario(jogadorAtacante);
        listarCartasCampo(jogadorDefensor);

        do {
            nomeMonstro = lerProximaLinha();
            //para o fluxo de execução caso o jogador escreva sair ao inves do nome de algum monstro
            if (escreveuSair(nomeMonstro)){
                break;
            }
            int posicaoMonstro = lerProximoNumero();
            if (naoEscreveuSair(nomeMonstro) && ehMonstroValido(jogadorDefensor, nomeMonstro)) {
                try {
                    //monstro adicionado na posição escolhida pelo usuario
                    listaCartasDefesa.set(posicaoMonstro-1, nomeMonstro);
                } catch (Exception IndexOutOfBoundsException) {
                    System.out.print("Escolha um numero dentro do intervalo solicitado!");
                }
            }
        } while (naoEscreveuSair(nomeMonstro));

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

    private void mensagemParaUsuario(Jogador jogadorAtacante) {
        System.out.printf("Escolha as cartas que serão usadas para defender, tecle enter" +
                " e em seguida digite a posição desejada (1 a %d) e tecle enter." +
                " Digite 'sair' para finalizar\n", numeroCartasAtacando(jogadorAtacante));
    }

    private void colocarCartasDefesa(Jogador jogadorDefensor, ArrayList<String> listaCartasDefesa) {
        for (String nomeCarta : listaCartasDefesa) {
            //pode retornar null quando a carta nao eh reconhecida
            Carta cartaParaDefesa = converterNomeCartaCampo(jogadorDefensor, nomeCarta);
            moverMonstro(jogadorDefensor, (Monstro) cartaParaDefesa);
        }
    }

    private void escolherMonstrosDefesaBot(Bot botDefensor, Jogador jogadorAtacante) {
        //perguntar a ele quais cartas ele quer colocar pro ataque (em ordem)
        int numeroCartasDefendendo = interagirComBotDefensor(botDefensor, jogadorAtacante);
        //todas as cartas sao validas
        colocarCartasDefesaBot(botDefensor, jogadorAtacante, numeroCartasDefendendo);
    }

    private int interagirComBotDefensor(Bot botDefensor, Jogador jogadorAtacante) {
        //escolher um numero aleatorio entre 0 e numero de cartas pertencentes ao campo de batalha adversario -1
        int numeroCartasAtacando = numeroCartasAtacando(jogadorAtacante);

        return botDefensor.getNumeroRandom(numeroCartasAtacando);
    }

    private int numeroCartasAtacando(Jogador jogadorAtacante) {
        return jogadorAtacante.getCartasBatalhando().size();
    }

    private void colocarCartasDefesaBot(Bot botDefensor, Jogador jogadorAtacante, int numeroCartasUsadasParaDefesa) {
        int numeroCartasAtacando = numeroCartasAtacando(jogadorAtacante);
        int numeroCartasCampo = numeroCartasCampo(botDefensor);
        ArrayList<Integer> listaIndicesUsadosDefesa = new ArrayList<>();
        ArrayList<Integer> listaIndicesUsadosCampo = new ArrayList<>();
        botDefensor.inicializarListaCartasDefesa(numeroCartasAtacando);
        if(numeroCartasCampo == 0){
            return;
        }
        while (numeroCartasUsadasParaDefesa > 0) {
            //me da o inidice aleatorio que a carta sera inserida
            int posicaoDefesa = botDefensor.getNumeroRandom(numeroCartasAtacando);
            //indice aleatorio do monstro em campo que será usado para defesa
            int posicaoCampo = botDefensor.getNumeroRandom(numeroCartasCampo);
            if (!listaIndicesUsadosDefesa.contains(posicaoDefesa)) {
                if (!listaIndicesUsadosCampo.contains(posicaoCampo)) {

                    Monstro monstro = botDefensor.getCartasEmCampo().get(posicaoCampo);

                    listaIndicesUsadosDefesa.add(posicaoDefesa);
                    listaIndicesUsadosCampo.add(posicaoCampo);

                    botDefensor.adicionarCartaDefesa(posicaoDefesa, monstro);
                    botDefensor.removerMonstroCampo(monstro);

                    numeroCartasUsadasParaDefesa--;
                    numeroCartasCampo--;
                }

            }
        }

    }

    private int numeroCartasCampo(Jogador jogador) {
        return jogador.getCartasEmCampo().size();
    }

    private void permitirBatalha(Jogador jogadorAtacante) {
        ArrayList<Monstro> monstrosEmBatalha = jogadorAtacante.getCartasBatalhando();
        int numeroMonstrosBatalha = monstrosEmBatalha.size();
        for (int i = 0; i < numeroMonstrosBatalha; i++) {
            Monstro monstro = monstrosEmBatalha.get(i);
            for (Efeito efeito : monstro.getListaEfeitos()) {
                if (podeUsarEfeito(efeito, MomentosDoTurno.ANTES_BATALHA)) {
                    monstro.ativarEfeito(efeito, jogadorAtacante, mesa, monstro);
                }
            }
            Jogador jogadorDefensor = acharJogadorDefensor(jogadorAtacante);
            monstro.atacar(jogadorDefensor, i);

        }
    }

    private Jogador acharJogadorDefensor(Jogador jogadorAtacante) {
        Jogador jogadorDefensor;
        if (jogadorAtacante == mesa.getJogador1()) {
            jogadorDefensor = mesa.getJogador2();
        } else {
            jogadorDefensor = mesa.getJogador1();
        }
        return jogadorDefensor;
    }


    private void verificarMonstrosFimDeTurno(Jogador jogador) {
        ArrayList<Monstro> cartasEmCampo = jogador.getCartasEmCampo();
        ArrayList <Monstro> campoAux = new ArrayList<>();
        for (Monstro monstro : cartasEmCampo){
            if (monstro.getVidaAtual() > 0){
                campoAux.add(monstro);
            }
        }
        jogador.substituirCampo(campoAux);
        cartasEmCampo = jogador.getCartasEmCampo();
        for (Monstro monstro : cartasEmCampo) {
            //colocar condiçao de que se a vida do monstro for menor ou igual a 0 nao pode usar o efeito
            if(monstro!=null){
                for (Efeito efeito : monstro.getListaEfeitos()) {
                    if(podeUsarEfeito(efeito, MomentosDoTurno.FIM_TURNO) ){
                        monstro.ativarEfeito(efeito,jogador,mesa,monstro);
                    }
                }
            }
        }

    }

    private void verificarMonstrosPosBatalha(Jogador jogador) {
        ArrayList<Monstro> cartasBatalhando = jogador.getCartasBatalhando();
        for (Monstro monstro : cartasBatalhando) {
            //colocar condiçao de que se a vida do monstro for menor ou igual a 0 nao pode usar o efeito
            if(monstro!=null){
                for (Efeito efeito : monstro.getListaEfeitos()) {
                    if(podeUsarEfeito(efeito, MomentosDoTurno.APOS_BATALHA) ){
                        monstro.ativarEfeito(efeito,jogador,mesa,monstro);
                    }
                }
            }
        }
        retornarMonstrosCampo(jogador);
    }

    private void retornarMonstrosCampo(Jogador jogador) {
        ArrayList<Monstro> cartasBatalhando = jogador.getCartasBatalhando();
        for (Monstro monstro : cartasBatalhando) {
            if(monstro!=null){
                if (monstro.getVidaAtual() > 0) {
                    //se a vida do monstro que sobrou for maior que 0, ele ainda está vivo e pode retornar ao campo
                    jogador.adicionaMonstroCampo(monstro);
                }
            }
        }
        jogador.limparCampoBatalha();
    }
}