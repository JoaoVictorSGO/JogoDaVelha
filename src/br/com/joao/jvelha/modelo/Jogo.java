package br.com.joao.jvelha.modelo;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import br.com.joao.jvelha.excecao.ModoDeJogoException;
import br.com.joao.jvelha.util.Contador;

public class Jogo {
	private Tabuleiro tabuleiro;
	private JogadorHumano jogador1;
	private JogadorHumano jogador2;
	private IA jogadorIA;
	private Contador contador;
	private Integer modoDeJogo;
	private Integer rodada = 1;
	private static final int TEMPO_MAXIMO = 10;
	
	
	public Jogo(Tabuleiro tabuleiro){
		this.tabuleiro = tabuleiro;
		this.contador = new Contador();
	}
	
	public CompletableFuture<String> inicializarContador() {
		return contador.iniciarComTempoMaximo(TEMPO_MAXIMO	, this::passarVez);
	}
	
	public void pararContador() {
		contador.pararContador();
	}
	private void modoDeJogoHumano() {
		jogador1 = new JogadorHumano(tabuleiro);
		if(modoDeJogo == 1) {
			jogador2 = new JogadorHumano(tabuleiro);
		}
	}
	
	public void selecionarModoDeJogo() {
		modoDeJogoHumano();
	}
	
	private boolean isModoHumanoVsHumano() {
	    return modoDeJogo == 1;
	}

	public void selecionarModoDeJogo(int dificuldade) {
		modoDeJogoHumano();
		if(!isModoHumanoVsHumano()) {
			jogadorIA = new IA(tabuleiro, tipoDeDificuldade(dificuldade));
		}
		
	}
	
	public Dificuldade tipoDeDificuldade(int dificuldade) {
		return switch (dificuldade) {
			case 1 -> Dificuldade.FACIL;
			case 2 -> Dificuldade.MEDIO;
			case 3 -> Dificuldade.DIFICIL;
			default -> throw new ModoDeJogoException("Dificuldade inválida: " + dificuldade);
		};
}
	
	public void selecionarSimbolo(char simbolo) {
		char simboloAdversario = simbolo == 'x' ? 'o' : 'x';
		
		if(isModoHumanoVsHumano()) {
			jogador1.setSimbolo(simbolo);
			jogador2.setSimbolo(simboloAdversario);
		}else {
			jogador1.setSimbolo(simbolo);
			jogadorIA.setSimbolo(simboloAdversario);
		}
		
		
	}
	
	public void sortearJogadorInicial() {
		boolean aleatorio = Math.random() < 0.5;
		
		if(isModoHumanoVsHumano()) {
			jogador1.setTurno(aleatorio);
			jogador2.setTurno(!aleatorio);
		}else {
			jogador1.setTurno(aleatorio);
			jogadorIA.setTurno(!aleatorio);
		}
	}
	
	public void reiniciarJogo() {
		jogador1 = null;
		jogador2 = null;
		modoDeJogo = 0;
		rodada = 1;
		tabuleiro.reniciarTabuleiro();
	}
	
	private void passarVez() {
		if(jogadorIA != null && jogadorIA.isTurno()) {
			jogadorIA.setTurno(false);
			jogador1.setTurno(true);
		}else if(jogador1.isTurno() && jogadorIA != null) {
			jogador1.setTurno(false);
			jogadorIA.setTurno(true);
		
		}else if (jogador2 != null && jogador2.isTurno() ){
			jogador2.setTurno(false);
			jogador1.setTurno(true);
	
		}else {
			jogador1.setTurno(false);
			jogador2.setTurno(true);
		}
	}
	
	public boolean jogada(int posicao) {
		if(jogador1.isTurno()) {
			tabuleiro.setJogador(jogador1);
			if(!jogador1.jogada(posicao)) {;
				return false;
			}
			rodada++;
			passarVez();
			return true;
			
		}else {
			if(Objects.equals(modoDeJogo, 1)) {
				tabuleiro.setJogador(jogador2);
				if(!jogador2.jogada(posicao)) {
					return false;
				};
				rodada++;
				passarVez();
				return true;
			}
		}
		return true;
	}
	
	public void jogadaIA() {
			tabuleiro.setJogador(jogadorIA);
			jogadorIA.jogadaIA();
			rodada++;
			passarVez();
	}
	
//	public boolean VerificarVencedor() {
//		
//	}
	
	
	public int getModoDeJogo() {
		return modoDeJogo;
	}

	public void setModoDeJogo(int modoDeJogo) {
		this.modoDeJogo = modoDeJogo;
	}

	public Jogador getJogador1() {
		return jogador1;
	}

	public Jogador getJogador2() {
		return jogador2;
	}

	public int getRodada() {
		return rodada;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public IA getJogadorIA() {
		return jogadorIA;
	}
	
}