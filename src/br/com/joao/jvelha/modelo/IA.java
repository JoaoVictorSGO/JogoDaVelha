package br.com.joao.jvelha.modelo;

import br.com.joao.jvelha.util.Direcao;

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
            jogada = jogadaMedia();
            adicionarSimbolo(jogada[0], jogada[1]);
        } else if (dificuldade == Dificuldade.DIFICIL) {
            jogada = jogadaDificil();
            adicionarSimbolo(jogada[0], jogada[1]);
        }
    }

    private int[] jogadaVencedora() {
        Direcao[] direcoes = { new Direcao(0, 1), new Direcao(1, 0), new Direcao(1, 1), new Direcao(1, -1) };
        for (int i = 0; i < 3; i++) {
            for (Direcao direcao : direcoes) {
                int x = (direcao.etapaX == 1) ? 0 : i;
                int y = (direcao.etapaY == 1 || direcao.etapaY == -1) ? ((direcao.etapaY == -1) ? 2 : 0) : i;
                int[] resultado = verificarLinhaColuna(x, y, direcao, simbolo);
                if (resultado != null)
                    return resultado;
            }
        }
        return null;
    }
    
    /**
     * Verifica uma linha/coluna/diagonal buscando duas ocorrências do símbolo indicado e uma posição vazia.
     */
    private int[] verificarLinhaColuna(int comecoX, int comecoY, Direcao direcao, char simboloBusca) {
        int count = 0;
        int[] posVazia = null;
        for (int i = 0; i < 3; i++) {
            int x = comecoX + (i * direcao.etapaX);
            int y = comecoY + (i * direcao.etapaY);
            if (tabuleiro.getTabuleiro()[x][y] == simboloBusca) {
                count++;
            } else if (tabuleiro.getTabuleiro()[x][y] == ' ') {
                posVazia = new int[] { x, y };
            }
        }
        return (count == 2 && posVazia != null) ? posVazia : null;
    }

    /**
     * Método que busca bloquear o adversário caso ele esteja a um movimento de vencer.
     */
    private int[] jogadaBloqueio() {
        char adversario = (simbolo == 'X') ? 'O' : 'X';
        Direcao[] direcoes = { new Direcao(0, 1), new Direcao(1, 0), new Direcao(1, 1), new Direcao(1, -1) };
        for (int i = 0; i < 3; i++) {
            for (Direcao direcao : direcoes) {
                int x = (direcao.etapaX == 1) ? 0 : i;
                int y = (direcao.etapaY == 1 || direcao.etapaY == -1) ? ((direcao.etapaY == -1) ? 2 : 0) : i;
                int[] resultado = verificarLinhaColuna(x, y, direcao, adversario);
                if (resultado != null)
                    return resultado;
            }
        }
        return null;
    }

    public void adicionarSimbolo(int linha, int coluna) {
        tabuleiro.adicionarSimbolo(linha, coluna);
    }

    public int[] jogadaFacil() {
        int tentativas = 0;
        int tentativasMaximas = 9;
        int[] coordenadas; 
        do {
            int posicao = (int) (Math.random() * 9);
            coordenadas = transformarCampo(posicao + 1);
            tentativas++;
        } while (!tabuleiro.verificarCampo(coordenadas[0], coordenadas[1]) && tentativas < tentativasMaximas);
        return (tabuleiro.verificarCampo(coordenadas[0], coordenadas[1])) ? coordenadas : null;
    }
    
    public int[] jogadaMedia() {
        return jogadaFacil();
    }
    
    /**
     * No nível difícil, antes de calcular a melhor jogada com Minimax,
     * verifica se o adversário possui jogada vencedora imediata e bloqueia.
     */
    public int[] jogadaDificil() {
        int[] bloqueio = jogadaBloqueio();
        if (bloqueio != null) {
            return bloqueio;
        }
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = null;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro.verificarCampo(i, j)) {
                    tabuleiro.getTabuleiro()[i][j] = simbolo;
                    int score = minimax(0, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
                    tabuleiro.getTabuleiro()[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[] { i, j };
                    }
                }
            }
        }
        return bestMove;
    }
    
    /**
     * Retorna o símbolo vencedor ('X' ou 'O') ou ' ' caso não haja vencedor.
     */
    private char getWinner() {
        char[][] board = tabuleiro.getTabuleiro();
        // Verifica linhas
        for (int i = 0; i < 3; i++) {
            if (board[i][0] != ' ' &&
                board[i][0] == board[i][1] &&
                board[i][1] == board[i][2]) {
                return board[i][0];
            }
        }
        // Verifica colunas
        for (int j = 0; j < 3; j++) {
            if (board[0][j] != ' ' &&
                board[0][j] == board[1][j] &&
                board[1][j] == board[2][j]) {
                return board[0][j];
            }
        }
        // Diagonais
        if (board[0][0] != ' ' &&
            board[0][0] == board[1][1] &&
            board[1][1] == board[2][2]) {
            return board[0][0];
        }
        if (board[0][2] != ' ' &&
            board[0][2] == board[1][1] &&
            board[1][1] == board[2][0]) {
            return board[0][2];
        }
        return ' ';
    }
    
    /**
     * Implementação do algoritmo Minimax com poda alfa-beta.
     * A função retorna uma pontuação que favorece jogadas que levem à vitória da IA
     * e penaliza jogadas que permitam ao adversário vencer.
     */
    private int minimax(int depth, boolean isMaximizing, int alpha, int beta) {
        char winner = getWinner();
        if (winner != ' ') {
            if (winner == simbolo) return 10 - depth;
            else return depth - 10;
        }
        if (tabuleiro.verificarVelha()) {
            return 0;
        }
        
        if (isMaximizing) {
            int maxEval = Integer.MIN_VALUE;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tabuleiro.verificarCampo(i, j)) {
                        tabuleiro.getTabuleiro()[i][j] = simbolo;
                        int eval = minimax(depth + 1, false, alpha, beta);
                        tabuleiro.getTabuleiro()[i][j] = ' ';
                        maxEval = Math.max(maxEval, eval);
                        alpha = Math.max(alpha, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return maxEval;
        } else {
            int minEval = Integer.MAX_VALUE;
            char adversario = (simbolo == 'X') ? 'O' : 'X';
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (tabuleiro.verificarCampo(i, j)) {
                        tabuleiro.getTabuleiro()[i][j] = adversario;
                        int eval = minimax(depth + 1, true, alpha, beta);
                        tabuleiro.getTabuleiro()[i][j] = ' ';
                        minEval = Math.min(minEval, eval);
                        beta = Math.min(beta, eval);
                        if (beta <= alpha) break;
                    }
                }
            }
            return minEval;
        }
    }
}
