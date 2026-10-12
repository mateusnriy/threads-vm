import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Parte I - Terminal de Atendimento Caixa (Cliente RPC Sincrono).
 *
 * Ao realizar uma chamada remota (ex: Auditoria Antifraude), a thread do caixa
 * fica BLOQUEADA esperando a resposta do Servidor Central (VM1).
 * O terminal nao consegue realizar nenhuma outra operacao durante esses 10 segundos.
 */
public class ClienteSincrono {
    private static final String IP_PADRAO = "192.168.10.10";
    private static final int PORTA_RMI = 1099;
    private static final String NOME_SERVICO = "ServicoBancarioRPC";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static String agora() {
        return LocalTime.now().format(FORMATTER);
    }

    public static void main(String[] args) {
        String ipServidor = args.length > 0 ? args[0] : IP_PADRAO;
        String nomeCliente = args.length > 1 ? args[1] : "CLIENTE 1";
        int tempoDemorado = args.length > 2 ? Integer.parseInt(args[2]) : 10;

        System.out.println("=============================================================");
        System.out.println("   PARTE I - TERMINAL CAIXA SINCRONO (" + nomeCliente + ")    ");
        System.out.println("=============================================================");
        System.out.println("[*] Conectando ao Servidor Central em " + ipServidor + ":" + PORTA_RMI + "...\n");

        try {
            Registry registry = LocateRegistry.getRegistry(ipServidor, PORTA_RMI);
            ServicoRemoto rpc = (ServicoRemoto) registry.lookup(NOME_SERVICO);
            System.out.println("[+] Conexao RPC bancaria estabelecida com sucesso!\n");

            // -------------------------------------------------------------
            // OPERACAO 1: Calculo simples (Sincrono - Conversao Cambial)
            // -------------------------------------------------------------
            System.out.println(">>> [OPERACAO 1] Calculo Imediato: Conversao Cambial (BRL -> USD)");
            System.out.println(agora() + " - Enviando requisicao RPC");
            System.out.println(agora() + " - Operacao solicitada: calcularConversaoCambio(R$ 5.000,00 | Taxa: 0.20)");
            long t1 = System.currentTimeMillis();
            double resCambio = rpc.calcularConversaoCambio(5000.00, 0.20);
            long duracaoCambio = (System.currentTimeMillis() - t1);
            System.out.println(agora() + " - Resposta recebida: US$ " + String.format("%.2f", resCambio));
            System.out.println(agora() + " - Tempo total: " + duracaoCambio + " ms\n");

            Thread.sleep(1000);

            // -------------------------------------------------------------
            // OPERACAO 2: Processamento demorado (Sincrono Bloqueante - Auditoria)
            // -------------------------------------------------------------
            System.out.println(">>> [OPERACAO 2] Processamento Demorado: Auditoria Antifraude de Risco");
            System.out.println(nomeCliente);
            System.out.println(agora() + " - Enviando requisicao RPC");
            System.out.println(agora() + " - Operacao solicitada: auditarTransacaoRisco(" + nomeCliente + ", R$ 500.000, " + tempoDemorado + "s)");

            long tInicioDemorado = System.currentTimeMillis();

            // PONTO DE BLOQUEIO: A thread do terminal fica travada aguardando a VM1
            String parecer = rpc.auditarTransacaoRisco(nomeCliente, 500000.00, tempoDemorado);

            long tempoTotalSegundos = (System.currentTimeMillis() - tInicioDemorado) / 1000;
            System.out.println(agora() + " - Resposta recebida");
            System.out.println(agora() + " - Detalhes do parecer: " + parecer);
            System.out.println(agora() + " - Tempo total: " + tempoTotalSegundos + " segundos\n");

            Thread.sleep(1000);

            // -------------------------------------------------------------
            // OPERACAO 3: Processamento de colecao (Sincrono - Fechamento de Balancete)
            // -------------------------------------------------------------
            System.out.println(">>> [OPERACAO 3] Processamento de Colecao: Balancete de Lote de Transacoes");
            double[] transacoes = { 150.00, 420.50, 1200.00, 89.90, 3500.00, 750.25, 230.10 };
            System.out.println(agora() + " - Enviando requisicao RPC");
            System.out.println(agora() + " - Operacao solicitada: processarBalanceteTransacoes(" + transacoes.length + " transacoes do caixa)");
            long t3 = System.currentTimeMillis();
            ResultadoBalancete balancete = rpc.processarBalanceteTransacoes(transacoes);
            long duracaoBalancete = System.currentTimeMillis() - t3;
            System.out.println(agora() + " - Resposta recebida: " + balancete);
            System.out.println(agora() + " - Tempo total: " + duracaoBalancete + " ms\n");

            System.out.println("[*] Execucao do Terminal Sincrono finalizada com sucesso!");

        } catch (Exception e) {
            System.err.println("[!] Erro durante chamada RPC no terminal: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
