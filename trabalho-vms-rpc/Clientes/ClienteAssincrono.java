import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.CompletableFuture;

/**
 * Parte II - Terminal de Atendimento Caixa Avancado (Cliente RPC Assincrono).
 *
 * Ao submeter uma transacao de alto valor para Auditoria de Risco no Servidor Central,
 * o terminal nao congela: a requisicao e despachada de forma nao bloqueante via CompletableFuture.
 * O operador do caixa continua atendendo clientes e executando tarefas locais normalmente
 * ate o momento em que a autorizacao remota fica disponivel.
 */
public class ClienteAssincrono {
    private static final String IP_PADRAO = "192.168.10.10";
    private static final int PORTA_RMI = 1099;
    private static final String NOME_SERVICO = "ServicoBancarioRPC";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static String agora() {
        return LocalTime.now().format(FORMATTER);
    }

    public static void main(String[] args) {
        String ipServidor = args.length > 0 ? args[0] : IP_PADRAO;
        String nomeCliente = args.length > 1 ? args[1] : "CLIENTE 1 (Terminal Assincrono)";
        int tempoDemorado = args.length > 2 ? Integer.parseInt(args[2]) : 10;

        System.out.println("=============================================================");
        System.out.println("   PARTE II - TERMINAL CAIXA ASSINCRONO (" + nomeCliente + ") ");
        System.out.println("=============================================================");
        System.out.println("[*] Conectando ao Servidor Central em " + ipServidor + ":" + PORTA_RMI + "...\n");

        try {
            Registry registry = LocateRegistry.getRegistry(ipServidor, PORTA_RMI);
            ServicoRemoto rpc = (ServicoRemoto) registry.lookup(NOME_SERVICO);
            System.out.println("[+] Conexao RPC bancaria estabelecida com sucesso!\n");

            // =========================================================================
            // DEMONSTRACAO PRINCIPAL: Auditoria de Risco Assincrona com Atividades Locais
            // =========================================================================
            System.out.println(">>> [DEMONSTRACAO PRINCIPAL] Solicitacao de Auditoria de Risco Assincrona");
            System.out.println("INICIO");

            // 1. Envio da requisicao assincrona retornando um Future imediatamente
            System.out.println(agora() + " - Requisicao assincrona enviada (Auditoria de Risco de R$ 500.000 - " + tempoDemorado + "s)");
            CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
                try {
                    return rpc.auditarTransacaoRisco(nomeCliente, 500000.00, tempoDemorado);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            // 2. Terminal continua imediatamente sua execucao
            System.out.println(agora() + " - Cliente continuou sua execucao");

            // Atividades locais tipicas de um caixa bancario enquanto espera o parecer da central
            String[] tarefasLocais = {
                "Emissao de comprovante fiscal e autenticacao mecanica local",
                "Conciliacao de saldo fisico da gaveta de dinheiro",
                "Registro de log de auditoria no disco local da agencia",
                "Verificacao de limites operacionais do caixa local",
                "Preparacao de interface para o proximo cliente da fila"
            };

            int tempoGastoLocal = 0;
            int indiceTarefa = 0;
            while (!future.isDone()) {
                Thread.sleep(2000);
                tempoGastoLocal += 2;
                String tarefa = (indiceTarefa < tarefasLocais.length) ? tarefasLocais[indiceTarefa] : "Processamento local em segundo plano";
                System.out.println(agora() + " - Executando atividade local " + (indiceTarefa + 1) + ": " + tarefa);
                indiceTarefa++;

                if (tempoGastoLocal >= tempoDemorado + 4) {
                    break;
                }
            }

            // 3. Resultado RPC disponivel
            String resultado = future.get();
            System.out.println(agora() + " - Resultado RPC disponivel:");
            System.out.println(agora() + " - " + resultado);
            System.out.println("FIM\n");

            Thread.sleep(1000);

            // =========================================================================
            // PIPELINE ADICIONAL: Operacoes bancarias paralelas combinadas
            // =========================================================================
            System.out.println(">>> [PIPELINE PARALELO] Disparando Cambio e Fechamento de Lote em paralelo...");

            CompletableFuture<Double> futureCambio = CompletableFuture.supplyAsync(() -> {
                try {
                    return rpc.calcularConversaoCambio(12500.00, 0.185);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            double[] loteTransacoes = { 250.0, 890.0, 1500.0, 45.0, 7800.0, 310.0 };
            CompletableFuture<ResultadoBalancete> futureBalancete = CompletableFuture.supplyAsync(() -> {
                try {
                    return rpc.processarBalanceteTransacoes(loteTransacoes);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            });

            CompletableFuture.allOf(futureCambio, futureBalancete).join();

            System.out.println(agora() + " - Conversao Cambial Assincrona Concluida: US$ " + String.format("%.2f", futureCambio.get()));
            System.out.println(agora() + " - Balancete de Lote Assincrono Concluido: " + futureBalancete.get());

            System.out.println("\n[*] Execucao do Terminal Assincrono finalizada com sucesso!");

        } catch (Exception e) {
            System.err.println("[!] Erro no Cliente RPC Assincrono: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
