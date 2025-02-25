package br.com.joao.jvelha.modelo;

public class IA extends Jogador{
	private Dificuldade dificuldade;
	
	public IA(Tabuleiro tabuleiro) {
		super(tabuleiro);
	}

	public Dificuldade getDificuldade() {
		return dificuldade;
	}

	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
	
	
	
}
