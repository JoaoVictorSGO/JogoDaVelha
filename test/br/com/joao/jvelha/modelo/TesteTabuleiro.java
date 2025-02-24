package br.com.joao.jvelha.modelo;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class TesteTabuleiro {
	Tabuleiro tabuleiro;
	Jogador jogador;
	@BeforeEach
	void iniciarTabuleiro()	{
		jogador = new Jogador(tabuleiro, 'x');
		tabuleiro = new Tabuleiro(jogador);
	}
	
	@Test
	void testeInicializarJogo() {
		for (int linha = 0; linha < tabuleiro.getTabuleiro().length ; linha++) {
			for (int coluna = 0; coluna < tabuleiro.getTabuleiro().length; coluna++) {
				assertEquals(' ', tabuleiro.getTabuleiro()[linha][coluna]);
			}		
		}
	}
	
	@Test
	void testeGetTabuleiro() {
		for(char[] tb : tabuleiro.getTabuleiro()) {
			for(char campo: tb) {
				assertEquals(' ' , campo);
			}
		}
	}
	
	@Test
	void testeNumero() {
		int maxCampo = tabuleiro.getTabuleiro()[0].length;
		assertEquals(3, maxCampo);
	}
	
	@Test
	void testVerificarCampo() {
		
		for (int i = 1; i <= 9; i++) {
			String[] coodernada = jogador.obterCoordenadas(i).split(",");
			int linha = Integer.parseInt(coodernada[0]);
			int coluna = Integer.parseInt(coodernada[1]);
			assertTrue(tabuleiro.verificarCampo(linha, coluna));
			
		}
	}
	
	@Test
	void testeAdicionarSimbolo() {
		
		for (int i = 1; i <= 9; i++) {
			String[] coodernada = jogador.obterCoordenadas(i).split(",");
			int linha = Integer.parseInt(coodernada[0]);
			int coluna = Integer.parseInt(coodernada[1]);
			assertTrue(tabuleiro.adicionarSimbolo(linha, coluna));
			
		}
	}
	
	@Test
	void testeVerificarVitoriaDiagonal1() {
		tabuleiro.adicionarSimbolo(0, 0);
		tabuleiro.adicionarSimbolo(1, 1);
		tabuleiro.adicionarSimbolo(2, 2);
		
		assertTrue(tabuleiro.verificarVitoria());
	}
	
	@Test
	void testeVerificarVitoriaDiagonal2() {
		tabuleiro.adicionarSimbolo(0, 2);
		tabuleiro.adicionarSimbolo(1, 1);
		tabuleiro.adicionarSimbolo(2, 0);
		
		assertTrue(tabuleiro.verificarVitoria());
	}
	@Test
	void testeVerificarVitoriaLinha() {
		for (int i = 0; i < 2; i++) {
			tabuleiro.adicionarSimbolo(i, 2);
			tabuleiro.adicionarSimbolo(i, 1);
			tabuleiro.adicionarSimbolo(i, 0);
			assertTrue(tabuleiro.verificarVitoria());
		}
	}
	
	@Test
	void testeVerificarVitoriaColuna() {
		for (int i = 0; i < 2; i++) {
			tabuleiro.adicionarSimbolo(0, i);
			tabuleiro.adicionarSimbolo(1, i);
			tabuleiro.adicionarSimbolo(2, i);
			assertTrue(tabuleiro.verificarVitoria());
		}
	}
}
