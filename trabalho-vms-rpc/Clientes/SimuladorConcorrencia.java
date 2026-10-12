/**
 * Utilitario auxiliar para simular a concorrencia simultanea entre dois caixas bancarios:
 * - VM2 (Caixa 1): Submete auditoria de risco de alta complexidade (10 segundos)
 * - VM3 (Caixa 2): Submete auditoria de risco simplificada (5 segundos)
 *
 * Permite comprovar que o Servidor Multithread da VM1 atende ambos simultaneamente
 * sem que o Caixa 2 fique travado esperando o Caixa 1.
 */
public class SimuladorConcorrencia {
    public static void main(String[] args) {
        String ipServidor = args.length > 0 ? args[0] : "127.0.0.1";

        System.out.println("=============================================================");
        System.out.println("   SIMULACAO DE CONCORRENCIA: CAIXA VM2 (10s) vs CAIXA VM3 (5s) ");
        System.out.println("=============================================================");

        Thread caixaVM2 = new Thread(() -> {
            System.out.println("[VM2 - Caixa 1] Submetendo auditoria de risco de 10 segundos...");
            ClienteSincrono.main(new String[]{ipServidor, "Caixa1-VM2", "10"});
        }, "Thread-Caixa1-VM2");

        Thread caixaVM3 = new Thread(() -> {
            try {
                // Pequeno delay de 250ms para garantir que a VM1 ja iniciou o processamento da VM2
                Thread.sleep(250);
            } catch (InterruptedException ignored) {}
            System.out.println("[VM3 - Caixa 2] Submetendo auditoria de risco de 5 segundos...");
            ClienteSincrono.main(new String[]{ipServidor, "Caixa2-VM3", "5"});
        }, "Thread-Caixa2-VM3");

        caixaVM2.start();
        caixaVM3.start();

        try {
            caixaVM2.join();
            caixaVM3.join();
            System.out.println("\n[+] Simulacao de concorrencia entre caixas finalizada com sucesso!");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
