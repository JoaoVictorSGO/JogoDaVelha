package br.com.joao.jvelha;

import br.com.joao.jvelha.modelo.Jogo;
import br.com.joao.jvelha.modelo.Tabuleiro;
import br.com.joao.jvelha.visao.TabuleiroConsole;

public class Aplicacao {
	public static void main(String[] args) {
		
		Tabuleiro tb = new Tabuleiro();
		Jogo jg = new Jogo(tb);
		new TabuleiroConsole(tb, jg);
		
	}
}
