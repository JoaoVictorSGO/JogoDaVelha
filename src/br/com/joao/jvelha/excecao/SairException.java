package br.com.joao.jvelha.excecao;

public class SairException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	public SairException(String texto) {
		super(texto);
	}
	
}
