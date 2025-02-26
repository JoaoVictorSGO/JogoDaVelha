package br.com.joao.jvelha.modelo;

import java.util.Iterator;

public class IA extends Jogador{
	private Dificuldade dificuldade;
	
	public IA(Tabuleiro tabuleiro, Dificuldade dificuldade) {
		super(tabuleiro);
		this.dificuldade = dificuldade;
	}
	public IA(Tabuleiro tabuleiro) {
		super(tabuleiro);
	}

	@Override
	public void jogada(int coluna) {
		if(dificuldade == Dificuldade.FACIL) {
			coluna = jogadaRandomica();
		}
		
		Iterator<Integer> coodenadas = trasformarCampo(coluna);
		tabuleiro.adicionarSimbolo(coodenadas.next(), coodenadas.next());
	}
	
	public int jogadaRandomica() {
		
		
		
		return (int)  (Math.random() * 9) + 1;
	}
	
}
