package br.com.joao.jvelha.modelo;

public class Tabuleiro {
	private char[][] tabuleiro = new char[3][3];
	Jogador jogador;
	public Tabuleiro() {
		inicializarTabuleiro();
	}
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
	void reniciarTabuleiro() {
		inicializarTabuleiro();
	}
	public char[][] getTabuleiro() {
		return tabuleiro;
	}
	
	public boolean verificarVitoria() {
		char simboloJogador = ' ';
		
		for (int c = 0; c < 2; c++) {
			if(c == 0) {
				 simboloJogador = 'x';
			}else {
				 simboloJogador = 'o';
			}
			for (int i = 0; i < 2; i++) {
				if (tabuleiro[i][0] == simboloJogador && tabuleiro[i][1] == simboloJogador
						&& tabuleiro[i][2] == simboloJogador) {
					return true;
				}
			}
			for (int i = 0; i < tabuleiro.length; i++) {
				if (tabuleiro[0][i] == simboloJogador && tabuleiro[1][i] == simboloJogador
						&& tabuleiro[0][i] == simboloJogador) {
					return true;
				}
			}
			if (tabuleiro[0][0] == simboloJogador && tabuleiro[1][1] == simboloJogador
					&& tabuleiro[2][2] == simboloJogador) {
				return true;
			}

			if (tabuleiro[0][2] == simboloJogador && tabuleiro[1][1] == simboloJogador
					&& tabuleiro[2][0] == simboloJogador) {
				return true;
			}
		}
		return false;
	}

	public Jogador getJogador() {
		return jogador;
	}
	public void setJogador(Jogador jogador) {
		this.jogador = jogador;
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
