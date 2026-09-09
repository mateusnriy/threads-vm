import java.io.*;
import java.net.*;

public class ServidorTCP {
    private static final int PORTA = 5000;

    public static void main(String[] args) {
        System.out.println("[*] Servidor TCP Java escutando na porta " + PORTA + "...");
        try (ServerSocket servidor = new ServerSocket(PORTA)) {
            while (true) {
                Socket cliente = servidor.accept();
                new Thread(new Atendente(cliente)).start();
            }
        } catch (IOException e) {
            System.err.println("[!] Erro no servidor TCP: " + e.getMessage());
        }
    }

    private static class Atendente implements Runnable {
        private final Socket socket;

        public Atendente(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            String ip = socket.getInetAddress().getHostAddress();
            long id = Thread.currentThread().threadId();
            System.out.println("\n[TCP] [+] Cliente " + ip + " conectado -> Atendido por Thread-" + id);

            try (
                BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                PrintWriter saida = new PrintWriter(socket.getOutputStream(), true)
            ) {
                String mensagem;
                while ((mensagem = entrada.readLine()) != null) {
                    if (mensagem.trim().equalsIgnoreCase("SAIR")) {
                        break;
                    }

                    System.out.println("[TCP] [Thread-" + id + "] Mensagem recebida de " + ip + ": '" + mensagem + "'");
                    System.out.println("[TCP] [Thread-" + id + "] Processando texto concorrente (pausa de 3s)...");
                    Thread.sleep(3000);

                    int totalChars = mensagem.length();
                    int totalPalavras = mensagem.trim().split("\\s+").length;
                    String resultado = String.format("PROCESSADO [Thread-%d]: %s (Caracteres: %d | Palavras: %d)",
                            id, mensagem.toUpperCase(), totalChars, totalPalavras);

                    saida.println(resultado);
                    System.out.println("[TCP] [Thread-" + id + "] Resposta enviada para " + ip);
                }
            } catch (Exception e) {
                System.err.println("[!] Erro na Thread-" + id + ": " + e.getMessage());
            } finally {
                try {
                    socket.close();
                } catch (IOException ignored) {}
                System.out.println("[TCP] [-] Conexao finalizada com " + ip + " (Thread-" + id + ")");
            }
        }
    }
}