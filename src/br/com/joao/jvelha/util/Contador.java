package br.com.joao.jvelha.util;

public class Contador {
	private  int segundos = 0;
	private  int minutos = 0;
	private  int horas = 0;
	private  boolean rodando = false;
	
	void incrementar() {
		segundos = (segundos + 1) % 60;
		if(segundos == 0) {
			minutos = (minutos + 1) % 60;
			if(minutos == 0) {
				horas++;
			}
		}
	}
	
	String formatarTempo() {
		return String.format("%02d:%02d:%02d", horas, minutos, segundos);
	}
	
	void pararContador() {
		rodando = false;
	}
	
	public String  iniciazarContador() {
		incrementar();
		return formatarTempo();
	}
}
