package br.com.joao.jvelha.util;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Contador implements Runnable {
    private int segundos = 0;
    private int minutos = 0;
    private int horas = 0;
    private boolean rodando = false;
    private Thread threadContador;
    private ScheduledExecutorService scheduler; 

    public void run() {
        while (rodando) {
            incrementar();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    private void incrementar() {
        segundos = (segundos + 1) % 60;
        if (segundos == 0) {
            minutos = (minutos + 1) % 60;
            if (minutos == 0) {
                horas++;
            }
        }
    }

    public String formatarTempo() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public void iniciarContador() {
        if (!rodando) {
            rodando = true;
            threadContador = new Thread(this);
            threadContador.start();
        }
    }

    public synchronized void pararContador() {
        rodando = false;
        if (threadContador != null && threadContador.isAlive()) {
            threadContador.interrupt();
        }
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }
    }

    public CompletableFuture<String> iniciarComTempoMaximo(int tempoMaximoSegundos, Runnable callback) {
        iniciarContador(); 
        CompletableFuture<String> resultado = new CompletableFuture<>();

        
        if (scheduler != null && !scheduler.isShutdown()) {
            scheduler.shutdownNow();
        }

        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            if (rodando) {
                pararContador();
                callback.run();
                resultado.complete("\nTempo Máximo! Trocando Jogador!\n");
            }
        }, tempoMaximoSegundos, TimeUnit.SECONDS);

        return resultado;
    }
}
