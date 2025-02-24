package br.com.joao.jvelha.modelo;

import br.com.joao.jvelha.util.Contador;

public class Jogo {
	private Tabuleiro tabuleiro;
	private Contador contador;
	private Jogador jogador;
	Jogo(Tabuleiro tabuleiro, Contador contador, Jogador jogador){
		this.tabuleiro = tabuleiro;
		this.contador = contador;
		this.jogador = jogador;
		iniciazarContador();
		
	}
	
	void iniciazarContador() { 
		for(int i = 0;i < 3665; i++) {
			System.out.println(contador.iniciazarContador());
		}
	}
	
	void selecionarSimbolo(char simbolo) {
		jogador.setSimbolo(simbolo);
	}
	
	
}
