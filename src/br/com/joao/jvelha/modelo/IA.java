package br.com.joao.jvelha.modelo;

import br.com.joao.jvelha.util.Direcao;

// modo medio ->  Algoritmo Aleatório com Heurísticas ou  Algoritmo Minimax Simples
// modo dificil ->  Minimax com Poda Alfa-Beta.
public class IA extends Jogador {
	private Dificuldade dificuldade;

	public IA(Tabuleiro tabuleiro, Dificuldade dificuldade) {
		super(tabuleiro);
		this.dificuldade = dificuldade;
	}

	public IA(Tabuleiro tabuleiro) {
		super(tabuleiro);
	}

	
	public void jogadaIA() {
		int[] jogada = jogadaVencedora();
		if (dificuldade == Dificuldade.FACIL) {
			if (jogada != null) {
				adicionarSimbolo(jogada[0], jogada[1]);
				return;
			}
			jogada = jogadaFacil();
			adicionarSimbolo(jogada[0], jogada[1]);
		} else if (dificuldade == Dificuldade.MEDIO) {
			if (jogada != null) {
				adicionarSimbolo(jogada[0], jogada[1]);
				return;
			}
		}
	}

	private int[] jogadaVencedora() {
		Direcao[] direcoes = { new Direcao(0, 1), new Direcao(1, 0), new Direcao(1, 1), new Direcao(1, -1) };
		for (int i = 0; i < 3; i++) {
			for (Direcao direcao : direcoes) {
				int x = (direcao.etapaX == 1) ? 0 : i;
				int y = (direcao.etapaY == 1 || direcao.etapaY == -1) ? ((direcao.etapaY == -1) ? 2 : 0) : i;
				int[] resultado = verificarLinhaColuna(x, y, direcao);
				if (resultado != null)
					return resultado;
			}
		}
		return null;
	}

	private int[] verificarLinhaColuna(int comecoX, int comecoY, Direcao direcao) {
		int count = 0;
		int[] posVazia = null;
		for (int i = 0; i < 3; i++) {
			int x = comecoX + (i * direcao.etapaX);
			int y = comecoY + (i * direcao.etapaY);

			if (tabuleiro.getTabuleiro()[x][y] == simbolo) {
				count++;
			} else if (tabuleiro.getTabuleiro()[x][y] == ' ') {
				posVazia = new int[] { x, y };
			}
		}
		return (count == 2 && posVazia != null) ? posVazia : null;
	}

	public void adicionarSimbolo(int linha, int coluna) {
		tabuleiro.adicionarSimbolo(linha, coluna);
	}

	public int[] jogadaFacil() {
		int tentativas = 0;
		int tentativasMaximas = 9;
		int[] coordenadas; 
		do {
			int posicao = (int)  (Math.random() * 9);
		    coordenadas = transformarCampo(posicao + 1);
		    tentativas++;
		}while(!tabuleiro.verificarCampo(coordenadas[0], coordenadas[1]) && tentativas < tentativasMaximas);
		 return (tabuleiro.verificarCampo(coordenadas[0], coordenadas[1])) ? coordenadas : null;
	}
}
