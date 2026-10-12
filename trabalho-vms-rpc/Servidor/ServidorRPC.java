import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Servidor RPC Central de Core Banking (executado na VM1 - Gateway/Servidor Interno).
 * Registra o servico remoto no RMI Registry na porta 1099.
 *
 * O Java RMI atende requisicoes dos caixas/terminais de forma MULTITHREAD nativa.
 */
public class ServidorRPC {
    public static final String NOME_SERVICO = "ServicoBancarioRPC";
    public static final int PORTA_RMI = 1099;
    public static final String IP_PADRAO_VM1 = "192.168.10.10";

    public static void main(String[] args) {
        String ipServidor = args.length > 0 ? args[0] : IP_PADRAO_VM1;

        // Garante que o RMI exporte o IP da interface da Rede B (192.168.10.10)
        System.setProperty("java.rmi.server.hostname", ipServidor);

        System.out.println("=============================================================");
        System.out.println("   CORE BANKING - SERVIDOR RPC CENTRAL MULTITHREAD (VM1)     ");
        System.out.println("=============================================================");
        System.out.println("[*] Configurando IP de bind RMI: " + ipServidor);
        System.out.println("[*] Porta do Registro RMI: " + PORTA_RMI);

        try {
            Registry registry;
            try {
                registry = LocateRegistry.createRegistry(PORTA_RMI);
                System.out.println("[+] Registro RMI criado na porta " + PORTA_RMI);
            } catch (Exception e) {
                registry = LocateRegistry.getRegistry(PORTA_RMI);
                System.out.println("[*] Registro RMI existente reaproveitado na porta " + PORTA_RMI);
            }

            ServicoRemoto servico = new ServicoRemotoImpl();
            registry.rebind(NOME_SERVICO, servico);

            System.out.println("[+] Servico remoto '" + NOME_SERVICO + "' publicado com sucesso!");
            System.out.println("[+] Servidor pronto para atender chamadas simultaneas dos caixas (VM2 e VM3).");
            System.out.println("[*] Aguardando requisicoes dos terminais...\n");

        } catch (Exception e) {
            System.err.println("[!] Erro fatal ao iniciar o Servidor Core Banking: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
