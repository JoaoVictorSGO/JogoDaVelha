package br.com.joao.jvelha.modelo;

import java.util.Arrays;
import java.util.Iterator;

import br.com.joao.jvelha.excecao.CampoInvalidoException;

public class Jogador {
	private Tabuleiro tabuleiro;
	private char simbolo;
	
	public Jogador(){
		
	}
	
	Jogador(Tabuleiro tabuleiro, char simbolo){
		this.tabuleiro = tabuleiro;
		this.simbolo = simbolo;
	}
	
	
	
//	boolean jogada(int campo) {
//		
//	}
	
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
	
	
	
	
	
	
	
}
