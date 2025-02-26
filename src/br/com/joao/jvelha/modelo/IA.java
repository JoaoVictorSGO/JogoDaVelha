package br.com.joao.jvelha.modelo;

import java.util.Iterator;

import br.com.joao.jvelha.util.Direcao;

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
			int[] jogadaVc = jogadaVencedora();
			if(jogadaVc != null) {
				tabuleiro.adicionarSimbolo(jogadaVc[0], jogadaVc[1]);
				return;
			}
			coluna = jogadaFacil();
			Iterator<Integer> coodenadas = trasformarCampo(coluna);
			while (!tabuleiro.adicionarSimbolo(coodenadas.next(), coodenadas.next())) {
				coluna = jogadaFacil();
				coodenadas = trasformarCampo(coluna);
			}
		}
	}
	
	public int jogadaFacil() {
		return (int)  (Math.random() * 9) + 1;
	}
	
	private int[] jogadaVencedora() {
		Direcao horizontal = new Direcao(0, 1);
		Direcao vertical = new Direcao(1, 0);
		Direcao diagonalPrincipal = new Direcao(1, 1);
		Direcao diagonalSecundaria = new Direcao(1, -1);
		for (int i = 0; i < 3; i++) {
			
			int[] linha = verificarLinhaColuna(i, 0, horizontal);
			if (linha != null) return linha;
			
			int[] coluna  = verificarLinhaColuna(0, i, vertical);
			if (coluna != null) return coluna;
		}
		
		int[] diagonal1 = verificarLinhaColuna(0, 0, diagonalPrincipal);
		if (diagonal1 != null) return diagonal1;
		
		int[] diagonal2 = verificarLinhaColuna(0, 2, diagonalSecundaria);
		if (diagonal2 != null) return diagonal2;
		
		return null;
	}
	
	
	private int[] verificarLinhaColuna(int comecoX, int comecoY, Direcao direcao) {
		int count = 0;
		int[] posVazia = null;
		for(int i = 0; i< 3; i++) {
			int x = comecoX + (i * direcao.etapaX);
			int y = comecoY + (i * direcao.etapaY);
			
			if(tabuleiro.getTabuleiro()[x][y] == simbolo) {
				count++;
			}else if(tabuleiro.getTabuleiro()[x][y] == ' ') {
				posVazia = new int[] {x,y};
			}
		}
		return (count == 2 && posVazia != null) ? posVazia : null;
	}
	
}
