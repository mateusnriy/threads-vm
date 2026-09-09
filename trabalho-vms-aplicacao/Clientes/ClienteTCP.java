import java.io.*;
import java.net.*;

public class ClienteTCP {
    private static final String SERVER_IP = "192.168.10.10";
    private static final int PORTA = 5000;

    public static void main(String[] args) {
        System.out.println("[*] Conectando ao Servidor TCP em " + SERVER_IP + ":" + PORTA + "...");

        try (
            Socket socket = new Socket(SERVER_IP, PORTA);
            PrintWriter saida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("[+] Conexao estabelecida!");

            String[] mensagens = {
                "Ola Servidor da VM1",
                "Trabalho de Sistemas Distribuidos",
                "Demonstracao de Concorrencia TCP"
            };

            for (String msg : mensagens) {
                System.out.println("\n[-] Enviando: '" + msg + "'");
                saida.println(msg);

                String resposta = entrada.readLine();
                System.out.println("[+] Resposta: " + resposta);
                Thread.sleep(1000);
            }

            saida.println("SAIR");
            System.out.println("\n[*] Finalizado com sucesso.");
        } catch (Exception e) {
            System.err.println("[!] Erro no cliente TCP: " + e.getMessage());
        }
    }
}