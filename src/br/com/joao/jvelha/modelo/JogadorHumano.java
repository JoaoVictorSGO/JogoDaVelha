package br.com.joao.jvelha.modelo;

public class JogadorHumano extends Jogador{
	
	public JogadorHumano(Tabuleiro tabuleiro) {
		super(tabuleiro);
	}
	
	
	public void jogada(int posicao) {
		int[] coodenadas = transformarCampo(posicao);
		tabuleiro.adicionarSimbolo(coodenadas[0], coodenadas[1]);
	}


}
