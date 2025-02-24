package br.com.joao.jvelha;

import br.com.joao.jvelha.modelo.Jogador;
import br.com.joao.jvelha.modelo.Tabuleiro;

public class Aplicacao {
	public static void main(String[] args) {
		Jogador jg = new Jogador();
		Tabuleiro tb = new Tabuleiro(jg);
		System.out.println(tb);
		
	}
}
