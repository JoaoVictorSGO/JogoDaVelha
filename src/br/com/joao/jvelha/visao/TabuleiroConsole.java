package br.com.joao.jvelha.visao;

import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

import br.com.joao.jvelha.excecao.CampoInvalidoException;
import br.com.joao.jvelha.excecao.SairException;
import br.com.joao.jvelha.modelo.Jogo;
import br.com.joao.jvelha.modelo.Tabuleiro;

public class TabuleiroConsole {
    private static final String ESCOLHA_SIMBOLO_MSG = "Escolha seu simbolo!";
    private static final String ESCOLHA_MODE_DE_JOGO_MSG = "Escolha o modo de jogo!" +
            "\n1 - Fácil" +
            "\n2 - Médio" +
            "\n3 - Difícil";
    private static final String BEM_VINDO = "------ Bem Vindo! ------";
    private static final String ESCOLHA_OPCAO_JOGO_MSG = "Você deseja jogar contra -> 1 - Humano / 2 - Computador?";
    private static final String ESCOLHA_CAMPO_MSG = "Digite um campo (1-9)";
    private static final String MSG_DE_SAIDA = "\nThau!!!\n";
    private static final String MSG_SOBRE_SIMBOLOS = "Atenção escolha somente 'x' ou 'o'";
    private static final String MSG_DE_INSTRUCAO = "\nATENÇÃO -> Digite 9 para ler as regras.\nATENÇÃO -> Digite 0 para encerrar a partida!\n";
    private static final String MSG_REGRAS = "\nREGRAS!!\nO jogo da velha é simples e divertido!" 
    		+ "Dois jogadores se revezam marcando \n\"X\" e \"O\" em um tabuleiro de 3x3."
    		+ "O objetivo é formar uma linha com três\nsímbolos iguais, seja na horizontal, vertical ou diagonal."
    		+ "Se todas as casas\nforem preenchidas sem um vencedor, o jogo termina em empate! Boa sorte\ne divirta-se\n\n";
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

    
	private void cicloDoJogo() {
		int modoDeJogo = 0;
		do {
		System.out.println(BEM_VINDO);
		System.out.println(MSG_DE_INSTRUCAO);
		modoDeJogo = capturarValorDigitadoInt(ESCOLHA_OPCAO_JOGO_MSG);
		if(modoDeJogo == 9) {
			regras(MSG_REGRAS);
		}
		} while(modoDeJogo == 9);
		addMododeJogo(modoDeJogo);
		configurarModoDeJogo();
		char simbolo = capturarValorDigitado(ESCOLHA_SIMBOLO_MSG);
		jogo.selecionarSimbolo(simbolo);
		jogo.sortearJogadorInicial();
		jogar();
		
	}
    private void regras(String texto) {
    	System.out.println(texto);
    }
	private void executarJogo() {
        try {
        	System.out.println();
            boolean continuar = true;
            while (continuar) {
                cicloDoJogo();
            }
        } catch (SairException e) {
            System.out.println(e.getMessage());
        }catch (InputMismatchException e) {
        	System.err.println("Erro: Entrada inválida!");
		}catch (NoSuchElementException e) {
			System.err.println("Erro: Nenhum elemento disponível para leitura.");
		}catch (IllegalStateException e) {
			 System.err.println("Erro: Scanner já foi fechado!");
		}catch (CampoInvalidoException e) {
			System.out.println("Campo Invalido!");
		}
    }
	
    private void addMododeJogo(int modoDeJogo) {
    	int modoDeJogoPadroa = 1;
    	if(modoDeJogo != 1 && modoDeJogo!= 2) {
    		System.out.println("modo de jogo incorreto/ alterar para padrão(1)\n");
    		jogo.setModoDeJogo(modoDeJogoPadroa);
    	}else {
    		jogo.setModoDeJogo(modoDeJogo);
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
    private void reiniciarJogo() {
    	jogo.reiniciarJogo();
    }
    
    private void vezDoJogador() {
   	 if (jogo.getJogador1().isTurno()) {
            System.out.println("Vez do Jogador 1");
        } else {
            System.out.println("Vez do Jogador 2");
        }
    }
    
    private void tempoDeRodada() {
		int rodadaConsole = jogo.getRodada();

		jogo.inicializarContador().thenAccept(mensagem -> {
			System.out.println(mensagem);
			vezDoJogador();
			System.out.println(tabuleiro);
			System.out.println(ESCOLHA_CAMPO_MSG);
			if (rodadaConsole == jogo.getRodada()) {
				tempoDeRodada();
			}
		});
	}
    
    private char capturarValorDigitado(String texto) {
        System.out.println(texto);
        String digitado = entrada.nextLine();
        digitado = digitado.trim().toLowerCase();
        if ("sair".equalsIgnoreCase(digitado)) {
            throw new SairException(MSG_DE_SAIDA);
        }
        while (!digitado.equals("x") && !digitado.equals("o")) {
			System.out.println(MSG_SOBRE_SIMBOLOS);
			digitado = entrada.nextLine();
		}
        return digitado.charAt(0);
    }
    
    private int capturarValorDigitadoInt(String texto) {
    	System.out.println(texto);
    	while (!entrada.hasNextInt()) {
			System.out.println("Entrada invalida! Digite apenas números.\n");
			entrada.next();
			System.out.println("Digite uma opção!\n");
		}
    	int digitado = entrada.nextInt();
    	if(digitado == 10) {
    		throw new SairException(MSG_DE_SAIDA);
    	}
    	rodadaConsole++;
    	entrada.nextLine();
    	
        return digitado;
    }
    private void jogada() {
    	if(jogo.getModoDeJogo() == 2 && jogo.getJogadorIA().isTurno() ) {
    		jogo.jogadaIA();
    	}else {
    		int digitado;
    		boolean jogada = true;;
    		do{
    			System.out.println("Rodada: " + jogo.getRodada());
    			if(!jogada) {
    				System.out.println("\nCampo Ocupadado! Digite novamente.\n");
    				System.out.println(tabuleiro);
    			}
    			vezDoJogador();
    			digitado = capturarValorDigitadoInt(ESCOLHA_CAMPO_MSG);
    			jogada = jogo.jogada(digitado);
    		}while(!jogada);
    	}
    }
    
    private void jogar() {
        do {
    
            System.out.println(tabuleiro);
            if (jogo.getModoDeJogo() == 1) {
                tempoDeRodada();  
                jogo.pararContador();
                jogada();
				
				
            } else {
                if (jogo.getJogador1().isTurno()) {
                    jogada();
                } else {
                    System.out.println("Vez do adversário!");
                    jogo.jogadaIA();
                    
                }
            }
        } while (!tabuleiro.verificarVitoria() && !tabuleiro.verificarVelha());

        System.out.println(tabuleiro);
        System.out.println("Fim!");
        reiniciarJogo();
    }
 
}