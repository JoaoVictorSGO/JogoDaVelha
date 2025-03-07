package br.com.joao.jvelha.modelo;

import java.util.Objects;

import br.com.joao.jvelha.excecao.SairException;

public abstract class Jogador  {
	protected Tabuleiro tabuleiro;
	protected boolean turno = false;
	protected boolean vitoria = false;
	protected char simbolo;
	private static final String MSG_CAMPO_SAIR = "\nThau!!!\n";
	Jogador(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
	}
	
	
	int[] transformarCampo(int posicao) {
		int minPosicao = 1;
		int maxPosicao = tabuleiro.getTabuleiro().length * tabuleiro.getTabuleiro()[0].length;
		if (posicao < minPosicao || posicao > maxPosicao ) {
			throw new SairException(MSG_CAMPO_SAIR);
		}
		int linha =(posicao - 1) / tabuleiro.getTabuleiro().length;
		int coluna = (posicao - 1) % tabuleiro.getTabuleiro().length;	
		return new int[] {linha, coluna};
	}

	public char getSimbolo() {
		return simbolo;
	}

	public void setSimbolo(char simbolo) {
		this.simbolo = simbolo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(simbolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jogador other = (Jogador) obj;
		return simbolo == other.simbolo;
	}

	public boolean isTurno() {
		return turno;
	}

	public void setTurno(boolean turno) {
		this.turno = turno;
	}

	public boolean isVitoria() {
		return vitoria;
	}

	public void setVitoria(boolean vitoria) {
		this.vitoria = vitoria;
	}
	
	
}
