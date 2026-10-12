import java.io.Serializable;

/**
 * Representa o relatorio consolidado de fechamento de balancete de transacoes financeiras.
 * Implementa Serializable para trafegar remotamente via Java RMI.
 */
public class ResultadoBalancete implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int totalTransacoes;
    private final double volumeTotal;
    private final double ticketMedio;
    private final double maiorTransacao;
    private final double menorTransacao;
    private final long tempoAnaliseMs;

    public ResultadoBalancete(int totalTransacoes, double volumeTotal, double ticketMedio,
                              double maiorTransacao, double menorTransacao, long tempoAnaliseMs) {
        this.totalTransacoes = totalTransacoes;
        this.volumeTotal = volumeTotal;
        this.ticketMedio = ticketMedio;
        this.maiorTransacao = maiorTransacao;
        this.menorTransacao = menorTransacao;
        this.tempoAnaliseMs = tempoAnaliseMs;
    }

    public int getTotalTransacoes() {
        return totalTransacoes;
    }

    public double getVolumeTotal() {
        return volumeTotal;
    }

    public double getTicketMedio() {
        return ticketMedio;
    }

    public double getMaiorTransacao() {
        return maiorTransacao;
    }

    public double getMenorTransacao() {
        return menorTransacao;
    }

    public long getTempoAnaliseMs() {
        return tempoAnaliseMs;
    }

    @Override
    public String toString() {
        return String.format(
            "Balancete [Qtd: %d ops | Volume Total: R$ %.2f | Ticket Medio: R$ %.2f | Maior: R$ %.2f | Menor: R$ %.2f | Tempo Servidor: %d ms]",
            totalTransacoes, volumeTotal, ticketMedio, maiorTransacao, menorTransacao, tempoAnaliseMs
        );
    }
}
