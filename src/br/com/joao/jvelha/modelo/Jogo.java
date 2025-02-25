package br.com.joao.jvelha.modelo;

import br.com.joao.jvelha.excecao.ModoDeJogoException;
import br.com.joao.jvelha.util.Contador;

public class Jogo {
	private Tabuleiro tabuleiro;
	private Contador contador;
	private Jogador jogador1;
	private Jogador jogador2;
	private int dificuldade;
	private int modoDeJogo;
	
	
	
	public Jogo(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
		
		//iniciazarContador();
		
	}
	
	void iniciazarContador() { 
		for(int i = 0;i < 3665; i++) {
			System.out.println(contador.iniciazarContador());
		}
	}
	
	public Dificuldade tipoDeDificuldade(int dificuldade) {
			this.dificuldade = dificuldade;
			return switch (dificuldade) {
			case 1 -> Dificuldade.FACIL;
			case 2 -> Dificuldade.MEDIO;
			case 3 -> Dificuldade.DIFICIL;
			default -> throw new ModoDeJogoException();
			};
	}
	
	
	public void selecionarModoDeJogo(int modo) {
		if(modo == 1) {
			
			jogador1 = new JogadorHumano(tabuleiro);
			jogador2 = new JogadorHumano(tabuleiro);
			modo = 1;
		}else {
			jogador1 = new JogadorHumano(tabuleiro);
			jogador2 = new IA(tabuleiro);
			modo = 2;
		}
	}
	
	public void selecionarSimbolo(char simbolo) {
		
		jogador1.setSimbolo(simbolo);
		jogador2.setSimbolo(simbolo == 'x' ? 'o' : 'x');
	}
	
	public void sortearJogadorInicial() {
		boolean aleatorio = Math.random() < 0.5;
		jogador1.setTurno(aleatorio);
		jogador2.setTurno(!aleatorio);
		
	}
	
	public void reniciarJogo() {
		jogador1 = null;
		jogador2 = null;
		tabuleiro.reniciarTabuleiro();
	}
	
	public void jogada(int coluna) {
		if(jogador1.isTurno()) {
			tabuleiro.setJogador(jogador1);
			jogadaDoJogador1(coluna);
		}else {
			tabuleiro.setJogador(jogador2);
			jogadaDoJogador2(coluna);
		}
	}
	
	void jogadaDoJogador1(int coluna) {
		jogador1.jogada(coluna);
		jogador1.setTurno(false);
		jogador2.setTurno(true);
	}
	void jogadaDoJogador2(int coluna) {
		jogador2.jogada(coluna);
		jogador2.setTurno(false);
		jogador1.setTurno(true);
	}
	public int getModoDeJogo() {
		return modoDeJogo;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}
	
	
	
}
