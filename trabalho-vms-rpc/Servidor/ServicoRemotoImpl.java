import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Implementacao do Servico Financeiro Central (Core Banking) na VM1.
 * O RMI gerencia nativamente um pool de threads multithread para atender
 * requisicoes simultaneas dos caixas e terminais (VM2 e VM3).
 */
public class ServicoRemotoImpl extends UnicastRemoteObject implements ServicoRemoto {
    private static final long serialVersionUID = 1L;
    private static final DateTimeFormatter HORA_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    public ServicoRemotoImpl() throws RemoteException {
        super();
    }

    private String getTimestamp() {
        return LocalTime.now().format(HORA_FORMATTER);
    }

    private String getThreadInfo() {
        return "Thread [" + Thread.currentThread().getName() + " - ID: " + Thread.currentThread().getId() + "]";
    }

    @Override
    public double calcularConversaoCambio(double valorMoedaOrigem, double taxaCambio) throws RemoteException {
        System.out.println(String.format(
            "[%s] [CORE-BANKING] %s -> Solicitacao: Conversao Cambial (Valor: R$ %.2f | Taxa: %.4f)",
            getTimestamp(), getThreadInfo(), valorMoedaOrigem, taxaCambio
        ));

        if (taxaCambio <= 0) {
            throw new RemoteException("A taxa cambial deve ser maior que zero.");
        }

        double resultado = valorMoedaOrigem * taxaCambio;

        System.out.println(String.format(
            "[%s] [CORE-BANKING] %s -> Sucesso: Conversao calculada = $ %.2f",
            getTimestamp(), getThreadInfo(), resultado
        ));
        return resultado;
    }

    @Override
    public String auditarTransacaoRisco(String idTerminal, double valorTransacao, int segundosAnalise) throws RemoteException {
        String threadInfo = getThreadInfo();
        System.out.println(String.format(
            "[%s] [CORE-BANKING] %s -> Iniciando Auditoria Antifraude de R$ %.2f para '%s' (Analise complexa: %ds)",
            getTimestamp(), threadInfo, valorTransacao, idTerminal, segundosAnalise
        ));

        long inicio = System.currentTimeMillis();
        try {
            Thread.sleep(segundosAnalise * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RemoteException("Auditoria de risco interrompida no servidor.", e);
        }
        long tempoExecutado = (System.currentTimeMillis() - inicio) / 1000;

        String parecer = String.format(
            "PARECER: APROVADO [Transacao R$ %.2f auditada com sucesso pelo %s em %ds para %s]",
            valorTransacao, threadInfo, tempoExecutado, idTerminal
        );

        System.out.println(String.format(
            "[%s] [CORE-BANKING] %s -> Finalizada Auditoria de '%s' (%ds de computacao)",
            getTimestamp(), threadInfo, idTerminal, tempoExecutado
        ));
        return parecer;
    }

    @Override
    public ResultadoBalancete processarBalanceteTransacoes(double[] transacoes) throws RemoteException {
        System.out.println(String.format(
            "[%s] [CORE-BANKING] %s -> Solicitacao: Fechamento de Balancete de Lote (%d transacoes)",
            getTimestamp(), getThreadInfo(), (transacoes != null ? transacoes.length : 0)
        ));

        if (transacoes == null || transacoes.length == 0) {
            throw new RemoteException("O lote de transacoes nao pode ser vazio.");
        }

        long inicio = System.currentTimeMillis();
        double volumeTotal = 0;
        double maior = transacoes[0];
        double menor = transacoes[0];

        for (double v : transacoes) {
            volumeTotal += v;
            if (v > maior) maior = v;
            if (v < menor) menor = v;
        }
        double ticketMedio = volumeTotal / transacoes.length;

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        long tempoMs = System.currentTimeMillis() - inicio;
        ResultadoBalancete balancete = new ResultadoBalancete(
            transacoes.length, volumeTotal, ticketMedio, maior, menor, tempoMs
        );

        System.out.println(String.format(
            "[%s] [CORE-BANKING] %s -> Balancete Consolidado com Sucesso: %s",
            getTimestamp(), getThreadInfo(), balancete
        ));
        return balancete;
    }
}
