package br.com.joao.jvelha.modelo;

public class JogadorHumano extends Jogador{
	
	public JogadorHumano(Tabuleiro tabuleiro) {
		super(tabuleiro);
	}
	
	
	public boolean jogada(int posicao) {
		int[] coodenadas = transformarCampo(posicao);
		if(!tabuleiro.verificarCampo(coodenadas[0], coodenadas[1])) {
			return false;
		}
		tabuleiro.adicionarSimbolo(coodenadas[0], coodenadas[1]);
		return true;
	}


}
