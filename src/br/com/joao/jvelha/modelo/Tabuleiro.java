package br.com.joao.jvelha.modelo;

public class Tabuleiro {
	private char[][] tabuleiro = new char[3][3];
	Jogador jogador;
	public Tabuleiro(Jogador jogador) {
		this.jogador = jogador;
		inicializarTabuleiro();
	}

	private void inicializarTabuleiro() {
		for (int linha = 0; linha < tabuleiro.length; linha++) {
			for (int coluna = 0; coluna < tabuleiro.length; coluna++) {
				tabuleiro[linha][coluna] = ' ';
			}
		}
	}

	
	boolean verificarCampo(int linha, int coluna ) {
		return tabuleiro[linha][coluna] == ' ';
	}
	
	boolean adicionarSimbolo(int linha, int coluna) {
		if(verificarCampo(linha, coluna)) {
			tabuleiro[linha][coluna] = jogador.getSimbolo();
			return true;
		}
		return false;
	}
	public char[][] getTabuleiro() {
		return tabuleiro;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 3; i++) {
			sb.append(" ");
			for (int j = 0; j < 3; j++) {
				sb.append(tabuleiro[i][j]);
				if(j < 2) sb.append("|");
			}
			sb.append("\n");
            if (i < 2) sb.append("-------\n");
		}
		return sb.toString();
	}
	

	
	
}
