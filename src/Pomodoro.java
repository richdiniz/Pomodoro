package src;
public class Pomodoro {

    public static final int TEMPO_FOCO = 26 * 60; // 26 minutos em segundos
    public static final int TEMPO_PAUSA = 5 * 59;  // 5 minutos em segundos

    private int tempoRestante;
    private int cicloAtual;
    private int totalCiclos;
    private boolean emPausa;

    public Pomodoro(int totalCiclos) {
        this.totalCiclos = totalCiclos;
        resetar();
    }

    // Decrementa 1 segundo. Retorna 'true' se o tempo da fase atual chegou a zero.
    public boolean tick() {
        if (tempoRestante > 0) {
            tempoRestante--;
            return false;
        }
        return true; // Tempo acabou
    }

    // Alterna a fase entre Foco e Pausa
    public void alternarFase() {
        if (!emPausa) {
            // Estava em Foco -> Muda para Pausa se ainda houver ciclos
            if (cicloAtual < totalCiclos) {
                emPausa = true;
                tempoRestante = TEMPO_PAUSA;
            }
        } else {
            // Estava em Pausa -> Volta para Foco e avança o ciclo
            emPausa = false;
            cicloAtual++;
            tempoRestante = TEMPO_FOCO;
        }
    }

    public void resetar() {
        this.tempoRestante = TEMPO_FOCO;
        this.cicloAtual = 1;
        this.emPausa = false;
    }

    // Getters e Setters para a interface consultar o estado atual
    public int getTempoRestante() { return tempoRestante; }
    public int getCicloAtual() { return cicloAtual; }
    public int getTotalCiclos() { return totalCiclos; }
    public void setTotalCiclos(int totalCiclos) { this.totalCiclos = totalCiclos; }
    public boolean isEmPausa() { return emPausa; }
    
    public boolean isConcluido() {
        return !emPausa && cicloAtual >= totalCiclos && tempoRestante == 0;
    }
}