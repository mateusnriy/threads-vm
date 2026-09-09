import java.net.*;

public class ServidorUDP {
    private static final int PORTA = 5001;

    public static void main(String[] args) {
        System.out.println("[*] Servidor UDP de Telemetria escutando na porta " + PORTA + "...");

        try (DatagramSocket socket = new DatagramSocket(PORTA)) {
            byte[] buffer = new byte[1024];

            while (true) {
                DatagramPacket pacote = new DatagramPacket(buffer, buffer.length);
                socket.receive(pacote);

                String texto = new String(pacote.getData(), 0, pacote.getLength()).trim();
                InetAddress ipCliente = pacote.getAddress();
                int portaCliente = pacote.getPort();

                new Thread(() -> {
                    long workerId = Thread.currentThread().threadId();
                    System.out.println("\n[UDP] [Worker-" + workerId + "] Pacote de " +
                                       ipCliente.getHostAddress() + ":" + portaCliente + " -> '" + texto + "'");

                    try {
                        System.out.println("[UDP] [Worker-" + workerId + "] Processando leitura (pausa de 2.5s)...");
                        Thread.sleep(2500);

                        String resposta = "ACK: Leitura recebida com sucesso! [Processado por Worker-" + workerId + "]";
                        byte[] dadosEnvio = resposta.getBytes();
                        DatagramPacket respostaPacote = new DatagramPacket(dadosEnvio, dadosEnvio.length, ipCliente, portaCliente);

                        socket.send(respostaPacote);
                        System.out.println("[UDP] [Worker-" + workerId + "] ACK enviado para " + ipCliente.getHostAddress());
                    } catch (Exception e) {
                        System.err.println("[!] Erro no Worker-" + workerId + ": " + e.getMessage());
                    }
                }).start();
            }
        } catch (Exception e) {
            System.err.println("[!] Erro no servidor UDP: " + e.getMessage());
        }
    }
}