package br.com.joao.jvelha.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TesteContador {
	
	private Contador contador;
	
	@BeforeEach
	void inicializar() {
		contador = new Contador();
	}
	
	@Test
	public void testIncrementar() {
		for (int i = 0; i < 59; i++) {
			contador.incrementar();
		}
		
		assertEquals("00:00:59", contador.formatarTempo());
		
		
		contador.incrementar();
		assertEquals("00:01:00", contador.formatarTempo());
		
		
		
		for (int i = 0; i < 59; i++) {
			contador.incrementar();
		}
		assertEquals("00:01:59", contador.formatarTempo());

		contador.incrementar();
		assertEquals("00:02:00", contador.formatarTempo());

	}
	
	@Test
	public void testIncrementar1hora() {
		for (int i = 0; i < 3600; i++) {
			contador.incrementar();
		}
		assertEquals("01:00:00", contador.formatarTempo());
	}
	
    @Test
    public void testFormatarTempo1Minuto() {
        
        for (int i = 0; i < 60; i++) {
            contador.incrementar();
        }
        assertEquals("00:01:00", contador.formatarTempo());
    }
    
    
    @Test
    public void testFormatarTempo1Hora() {
    	
    	for (int i = 0; i < 3600; i++) {
    		contador.incrementar();
    	}
    	assertEquals("01:00:00", contador.formatarTempo());
    }
}

