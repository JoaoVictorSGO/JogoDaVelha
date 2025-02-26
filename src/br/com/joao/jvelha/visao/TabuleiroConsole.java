package br.com.joao.jvelha.visao;

import java.util.Scanner;

import br.com.joao.jvelha.excecao.SairException;
import br.com.joao.jvelha.modelo.Jogo;
import br.com.joao.jvelha.modelo.Tabuleiro;

public class TabuleiroConsole {
	private Tabuleiro tabuleiro;
	private Jogo jogo;
	private Scanner entrada = new Scanner(System.in);
	
	public TabuleiroConsole(Tabuleiro tabuleiro, Jogo jogo) {
		this.jogo = jogo;
		this.tabuleiro = tabuleiro;
		executarJogo();
	}
	
	private void executarJogo() {
		try {
			boolean continuar = true;
			while (continuar) {
				cicloDoJogo();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void cicloDoJogo() {
		try {
				System.out.println("------Bem Vindo!------");
				System.out.println("Com quem você deseja jogar?");
				int digitado = capturarValorDigitadoInt("1 - Humano/ 2 - Computador?"); 
				jogo.setModoDeJogo(digitado);
				
				if(jogo.getModoDeJogo() == 2){
					digitado = Integer.parseInt(capturarValorDigitado("Escolha o modo de jogo!" +
						"\n1-Facil"+
						"\n2-Medio"+
						"\n3-Dificil"));
					jogo.selecionarModoDeJogo(digitado);
				}else {
					jogo.selecionarModoDeJogo();
				}
					
					String simbolo = capturarValorDigitado("Escolha seu simbolo!");
					jogo.selecionarSimbolo(simbolo.charAt(0));
					jogo.sortearJogadorInicial();
					
			do {
				
				if(jogo.getModoDeJogo() == 1) {
					System.out.println(tabuleiro);
					if(jogo.getJogador2().isTurno()) {
					System.out.println("Vez do Jogador 2");
					}else {
					System.out.println("Vez do Jogador 1");
					}
					digitado = capturarValorDigitadoInt("Digite um campo 1/9");
					jogo.jogada(digitado);
				}else {
					System.out.println(tabuleiro);
					if(jogo.getJogador1().isTurno()) {
						System.out.println("Vez do Jogador 1");
						digitado = capturarValorDigitadoInt("Digite um campo 1/9");
						jogo.jogada(digitado);
						
					}else {
						System.out.println("Vez do adversario!");
						jogo.jogadaIA();
						
					}
				}
				
				
				
			}while (!tabuleiro.verificarVitoria() && !tabuleiro.verificarVelha());
			
			System.out.println(tabuleiro);
			System.out.println("fim!");
			jogo.reniciarJogo();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	private String capturarValorDigitado(String texto) {
		System.out.println(texto);
		String digitado = entrada.nextLine();
		if("sair".equalsIgnoreCase(digitado)) {
			throw new SairException();
		}
		
		return digitado;
	}
	private int capturarValorDigitadoInt(String texto) {
		System.out.println(texto);
		int digitado = entrada.nextInt();
		entrada.nextLine();
		return digitado;
	}
}
