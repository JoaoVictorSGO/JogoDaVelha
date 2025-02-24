package br.com.joao.jvelha.modelo;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

import br.com.joao.jvelha.excecao.CampoInvalidoException;

public class Jogador {
	private Tabuleiro tabuleiro;
	private boolean turno = false;
	private char simbolo;
	
	public Jogador(){
		
	}
	
	Jogador(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
		
	}
	
	void jogada(int campo) {
		Iterator<Integer> jg = trasformarCampo(campo);
		tabuleiro.adicionarSimbolo(jg.next(), jg.next());
	}
	
	String obterCoordenadas(int campo) {
		return switch (campo) {
		case 1 -> "0,0";
		case 2 -> "0,1";
		case 3 -> "0,2";
		case 4 -> "1,0";
		case 5 -> "1,1";
		case 6 -> "1,2";
		case 7 -> "2,0";
		case 8 -> "2,1";
		case 9 -> "2,2";
		default ->  throw new CampoInvalidoException();
		};	
	}
	
	Iterator<Integer> trasformarCampo(int campo) {
		int minCampo = 1;
		int maxCampo = tabuleiro.getTabuleiro().length * tabuleiro.getTabuleiro()[0].length;
		if (campo < minCampo || campo > maxCampo ) {
			throw new CampoInvalidoException();
		}
		
		return Arrays.stream(obterCoordenadas(campo).split(",")).
				map(e -> Integer.parseInt(e.trim()))
				.iterator();
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
	
}
