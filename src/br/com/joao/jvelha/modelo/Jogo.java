package br.com.joao.jvelha.modelo;

import br.com.joao.jvelha.util.Contador;

public class Jogo {
	private Tabuleiro tabuleiro;
	private Contador contador;
	private Jogador jogador1;
	private Jogador jogador2;
	Jogo(Tabuleiro tabuleiro, Contador contador){
		this.tabuleiro = tabuleiro;
		this.contador = contador;
		iniciazarContador();
		
	}
	
	void iniciazarContador() { 
		for(int i = 0;i < 3665; i++) {
			System.out.println(contador.iniciazarContador());
		}
	}
	
	void selecionarModoDeJogo(int modo) {
		if(modo == 1) {
			jogador1 = new Jogador(tabuleiro);
			jogador2 = new Jogador(tabuleiro);
		}else {
			
		}
	}
	void selecionarSimbolo(char simbolo) {
		jogador1.setSimbolo(simbolo);
		jogador2.setSimbolo(simbolo == 'x' ? 'o' : 'x');
	}
	
	private void sortearJogadorInicial() {
		boolean aleatorio = Math.random() < 0.5;
		jogador1.setTurno(aleatorio);
		jogador2.setTurno(!aleatorio);
		
	}
	
	void jogada(int coluna) {
		if(jogador1.isTurno()) {
			jogadaDoJogador1(coluna);
		}else {
			jogadaDoJogador2(coluna);
		}
	}
	
	void jogadaDoJogador1(int coluna) {
		jogador1.jogada(coluna);
		jogador1.setTurno(false);
		jogador2.setTurno(true);
	}
	void jogadaDoJogador2(int coluna) {
		jogador2.jogada(coluna);
		jogador2.setTurno(false);
		jogador1.setTurno(true);
	}
	
}
