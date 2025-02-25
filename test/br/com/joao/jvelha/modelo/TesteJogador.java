package br.com.joao.jvelha.modelo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Iterator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import br.com.joao.jvelha.excecao.CampoInvalidoException;

public class TesteJogador {
	Jogador jogador ;
	Tabuleiro tabuleiro;
	
	@BeforeEach
	void iniciarJogador() {
		tabuleiro = new Tabuleiro(jogador);
		jogador = new Jogador(tabuleiro);
		
		
	}
	
	@Test
	void testeObterCoordenadas() {
		assertEquals("0,0", jogador.obterCoordenadas(1));
		assertEquals("0,1", jogador.obterCoordenadas(2));
		assertEquals("0,2", jogador.obterCoordenadas(3));
		assertEquals("1,0", jogador.obterCoordenadas(4));
		assertEquals("1,1", jogador.obterCoordenadas(5));
		assertEquals("1,2", jogador.obterCoordenadas(6));
		assertEquals("2,0", jogador.obterCoordenadas(7));
		assertEquals("2,1", jogador.obterCoordenadas(8));
		assertEquals("2,2", jogador.obterCoordenadas(9));
	}
	@Test
	void testeObterCoordenadasExcecao() {
		assertThrows(CampoInvalidoException.class, () -> {
			jogador.obterCoordenadas(10);
		});
	}
	
	@Test
	void testeTrasformarCampo() { 
		for (int i = 1; i <= 9; i++) {
			Iterator<Integer> campo = jogador.trasformarCampo(i);
			String[] coordenada = jogador.obterCoordenadas(i).split(",");
	
			assertEquals(Integer.parseInt(coordenada[0]) , campo.next());
			assertEquals(Integer.parseInt(coordenada[1]), campo.next());
		}
	}
	
	@SuppressWarnings("unused")
	@Test
	void testeTrasformarCampoExcecao() { 
		assertThrows(CampoInvalidoException.class, () ->{
			Iterator<Integer> campo = jogador.trasformarCampo(10);
		});
		
	}

}
