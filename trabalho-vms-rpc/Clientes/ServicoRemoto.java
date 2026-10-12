import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface RPC (Java RMI) do Sistema Financeiro Corporativo (Core Banking).
 * Disponibiliza procedimentos remotos entre o Servidor Central (VM1) e os Terminais/Caixas (VM2 e VM3).
 */
public interface ServicoRemoto extends Remote {

    /**
     * Operacao 1: Calculo simples.
     * Realiza a conversao de um valor monetario com base em uma taxa cambial.
     */
    double calcularConversaoCambio(double valorMoedaOrigem, double taxaCambio) throws RemoteException;

    /**
     * Operacao 2: Processamento demorado.
     * Simula a analise de credito e auditoria de risco de alta complexidade para transacoes de alto valor,
     * com tempo artificialmente parametrizavel (ex: 5s, 10s).
     */
    String auditarTransacaoRisco(String idTerminal, double valorTransacao, int segundosAnalise) throws RemoteException;

    /**
     * Operacao 3: Processamento de colecao.
     * Recebe um lote de valores de transacoes financeiras e consolida o balancete.
     */
    ResultadoBalancete processarBalanceteTransacoes(double[] transacoes) throws RemoteException;
}
