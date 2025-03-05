package br.com.joao.jvelha.visao;

import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import br.com.joao.jvelha.excecao.SairException;
import br.com.joao.jvelha.modelo.Jogo;
import br.com.joao.jvelha.modelo.Tabuleiro;

public class TabuleiroConsole {
    private static final String ESCOLHA_SIMBOLO_MSG = "Escolha seu simbolo!";
    private static final String ESCOLHA_MODE_DE_JOGO_MSG = "Escolha o modo de jogo!" +
            "\n1 - Fácil" +
            "\n2 - Médio" +
            "\n3 - Difícil";
    private static final String ESCOLHA_OPCAO_JOGO_MSG = "1 - Humano / 2 - Computador?";
    private static final String ESCOLHA_CAMPO_MSG = "Digite um campo (1-9)";
    private Tabuleiro tabuleiro;
    private Jogo jogo;
    private Scanner entrada = new Scanner(System.in);
    private int rodadaConsole;
    public TabuleiroConsole(Tabuleiro tabuleiro, Jogo jogo) {
        this.jogo = jogo;
        this.tabuleiro = tabuleiro;
        this.rodadaConsole = jogo.getRodada();
        executarJogo();
    }

    private void executarJogo() {
        try {
            boolean continuar = true;
            while (continuar) {
                cicloDoJogo();
            }
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    private void cicloDoJogo() {
        try {
            System.out.println("------ Bem Vindo! ------");
            int modoDeJogo = capturarValorDigitadoInt(ESCOLHA_OPCAO_JOGO_MSG);
            jogo.setModoDeJogo(modoDeJogo);

            configurarModoDeJogo();

            String simbolo = capturarValorDigitado(ESCOLHA_SIMBOLO_MSG);
            jogo.selecionarSimbolo(simbolo.charAt(0));
            jogo.sortearJogadorInicial();

            jogar();
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
    
    private void configurarModoDeJogo() {
        if (jogo.getModoDeJogo() == 2) {
            int dificuldade = capturarValorDigitadoInt(ESCOLHA_MODE_DE_JOGO_MSG);
            jogo.selecionarModoDeJogo(dificuldade);
        } else {
            jogo.selecionarModoDeJogo();
        }
    }
    
    private void jogar() {
        int digitado;
        
        do {
        	
            System.out.println(tabuleiro);
            if (jogo.getModoDeJogo() == 1) {
                vezDoJogador();
                tempoDeRodada();  
                System.out.println("Rodada: " + jogo.getRodada());
                digitado = capturarValorDigitadoInt(ESCOLHA_CAMPO_MSG);
                jogo.pararContador();
                jogo.jogada(digitado);
				
				
            } else {
                if (jogo.getJogador1().isTurno()) {
                    System.out.println("Vez do Jogador 1");
                    digitado = capturarValorDigitadoInt(ESCOLHA_CAMPO_MSG);
                    jogo.jogada(digitado);
                } else {
                    System.out.println("Vez do adversário!");
                    jogo.jogadaIA();
                }
            }
        } while (!tabuleiro.verificarVitoria() && !tabuleiro.verificarVelha());

        System.out.println(tabuleiro);
        System.out.println("Fim!");
        jogo.reiniciarJogo();;
    }
    
    private void vezDoJogador() {
    	 if (jogo.getJogador2().isTurno()) {
             System.out.println("Vez do Jogador 2");
         } else {
             System.out.println("Vez do Jogador 1");
         }
    }
    private String capturarValorDigitado(String texto) {
        System.out.println(texto);
        String digitado = entrada.nextLine();
        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException();
        }
        return digitado;
    }
    
    private void tempoDeRodada() {
    	int rodadaConsole = jogo.getRodada();
		jogo.inicializarContador().thenAccept(mensagem -> {
        System.out.println(mensagem);
        vezDoJogador();
        System.out.println(tabuleiro);
        System.out.println("Digite um campo (1-9)"); 
        if(rodadaConsole == jogo.getRodada()) {
        	tempoDeRodada();
        }
		});
    }

    private CompletableFuture<Integer> capturarValorDigitadoAsync(String texto) {
    	return CompletableFuture.supplyAsync(() -> capturarValorDigitadoInt(texto));
    	
    	
    	
    }
    
    private int capturarValorDigitadoInt(String texto) {
    	
    	System.out.println(texto);
    	rodadaConsole++;
    	int digitado = entrada.nextInt();
    	rodadaConsole++;
    	entrada.nextLine();
        return digitado;
    }

    
    
   
}