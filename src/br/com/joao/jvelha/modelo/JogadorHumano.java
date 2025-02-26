package br.com.joao.jvelha.modelo;

import java.util.Iterator;

public class JogadorHumano extends Jogador{
	
	public JogadorHumano(Tabuleiro tabuleiro) {
		super(tabuleiro);
	}
	
	@Override
	public void jogada(int coluna) {
		Iterator<Integer> coodenadas = trasformarCampo(coluna);
		tabuleiro.adicionarSimbolo(coodenadas.next(), coodenadas.next());
	}
}
