import java.net.*;

public class ClienteUDP {
    private static final String SERVER_IP = "192.168.10.10";
    private static final int PORTA = 5001;

    public static void main(String[] args) {
        System.out.println("[*] Enviando leituras UDP para " + SERVER_IP + ":" + PORTA + "...");

        try (DatagramSocket socket = new DatagramSocket()) {
            socket.setSoTimeout(5000);
            InetAddress ipServidor = InetAddress.getByName(SERVER_IP);
            String maquina = InetAddress.getLocalHost().getHostName();

            String[] dados = {
                "Maquina: " + maquina + " | Status: OPERACIONAL",
                "Maquina: " + maquina + " | Status: PROCESSANDO_DADOS",
                "Maquina: " + maquina + " | Status: FINALIZANDO_TESTE"
            };

            for (String item : dados) {
                System.out.println("\n[-] Enviando: '" + item + "'");
                byte[] bufferEnvio = item.getBytes();
                DatagramPacket envio = new DatagramPacket(bufferEnvio, bufferEnvio.length, ipServidor, PORTA);
                socket.send(envio);

                try {
                    byte[] bufferResposta = new byte[1024];
                    DatagramPacket resposta = new DatagramPacket(bufferResposta, bufferResposta.length);
                    socket.receive(resposta);

                    String ack = new String(resposta.getData(), 0, resposta.getLength());
                    System.out.println("[+] Servidor respondeu: " + ack);
                } catch (SocketTimeoutException e) {
                    System.err.println("[!] Timeout: O servidor nao respondeu a tempo.");
                }

                Thread.sleep(1000);
            }

            System.out.println("\n[*] Envio UDP concluido.");
        } catch (Exception e) {
            System.err.println("[!] Erro no cliente UDP: " + e.getMessage());
        }
    }
}
