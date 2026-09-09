# Trabalho de Sistemas Distribuídos - Aplicação Cliente-Servidor Concorrente em VMs (TCP e UDP)

Este projeto implementa uma aplicação distribuída baseada no modelo **Cliente-Servidor**, utilizando comunicação via **Sockets TCP e UDP** em Java. O sistema foi projetado para suportar múltiplos clientes simultâneos por meio de concorrência com threads, sendo executado e testado em ambiente de rede distribuído entre Máquinas Virtuais (VMs).

---

## Discentes

* **Kaio França**
* **Mateus Neri**

---

## Vídeo de Demonstração e Explicação

O vídeo com o passo a passo da configuração do ambiente de rede entre as Máquinas Virtuais (VMs), a execução dos servidores concorrentes e os testes com múltiplos clientes TCP e UDP foi publicado no YouTube:

* ▶️ **Link do Vídeo no YouTube:** [https://www.youtube.com/watch?v=h8lpCKpLrb8](https://www.youtube.com/watch?v=h8lpCKpLrb8)

---

## Arquitetura e Funcionamento do Sistema

A aplicação é dividida em dois pilares de comunicação de rede:

### 1. Comunicação TCP Concorrente (Porta `5000`)
* **Protocolo:** Orientado a conexão, confiável e bidirecional.
* **Servidor (`ServidorTCP.java`):** Escuta conexões na porta `5000`. A cada conexão recebida (`serverSocket.accept()`), instancia uma nova thread (`Atendente`) dedicada ao cliente. O atendente processa mensagens de texto (contagem de caracteres, contagem de palavras e conversão para maiúsculas) simulando uma carga de trabalho de 3 segundos, demonstrando o processamento assíncrono e concorrente sem bloquear novos clientes.
* **Cliente (`ClienteTCP.java`):** Conecta-se ao IP configurado do servidor, envia uma sequência de mensagens, recebe as respostas processadas e encerra a sessão de forma graciosa através do comando `SAIR`.

### 2. Comunicação UDP com Telemetria (Porta `5001`)
* **Protocolo:** Não orientado a conexão baseado em datagramas (`DatagramSocket` e `DatagramPacket`).
* **Servidor (`ServidorUDP.java`):** Escuta na porta `5001`. Ao receber pacotes de dados, despacha a requisição para uma thread worker assíncrona (`Worker-ID`), a qual simula o processamento dos dados recebidos (2.5 segundos) e devolve um pacote de confirmação (**ACK**) para o IP e porta de origem do cliente.
* **Cliente (`ClienteUDP.java`):** Obtém o hostname da máquina local e envia pacotes de telemetria/status para o servidor, aguardando a confirmação (**ACK**) com controle de timeout configurado para 5 segundos (`SocketTimeoutException`).

---

## Estrutura do Diretório

```text
trabalho-vms-aplicacao/
├── Clientes/
│   ├── ClienteTCP.java      # Cliente para envio de mensagens via socket TCP
│   └── ClienteUDP.java      # Cliente de telemetria via datagramas UDP
├── Servidor/
│   ├── ServidorTCP.java     # Servidor multithreaded TCP (Porta 5000)
│   └── ServidorUDP.java     # Servidor multithreaded UDP (Porta 5001)
└── README.md                # Documentação do trabalho
```

---

## Como Configurar e Executar

### Pré-requisitos
* **JDK (Java Development Kit) 8 ou superior** instalado nas máquinas/VMs.
* Conectividade de rede configurada entre as Máquinas Virtuais (ex: IPs estáticos ou na mesma sub-rede privada).

> **Atenção:** Certifique-se de que a constante `SERVER_IP` nos arquivos de cliente (`ClienteTCP.java` e `ClienteUDP.java`) esteja apontando para o IP correto da VM Servidor (por padrão definido como `192.168.10.10` ou o IP correspondente da sua rede).

---

### Passo 1: Compilação dos Códigos

No terminal da **VM Servidor**:
```bash
cd trabalho-vms-aplicacao/Servidor
javac ServidorTCP.java ServidorUDP.java
```

No terminal da **VM Cliente**:
```bash
cd trabalho-vms-aplicacao/Clientes
javac ClienteTCP.java ClienteUDP.java
```

---

### Passo 2: Execução dos Servidores (VM Servidor)

Em terminais separados na máquina/VM do Servidor:

* **Iniciar Servidor TCP:**
  ```bash
  cd trabalho-vms-aplicacao/Servidor
  java ServidorTCP
  ```

* **Iniciar Servidor UDP:**
  ```bash
  cd trabalho-vms-aplicacao/Servidor
  java ServidorUDP
  ```

---

### Passo 3: Execução dos Clientes (VM(s) Cliente(s))

Em terminais nas máquinas/VMs clientes:

* **Executar Cliente TCP:**
  ```bash
  cd trabalho-vms-aplicacao/Clientes
  java ClienteTCP
  ```

* **Executar Cliente UDP:**
  ```bash
  cd trabalho-vms-aplicacao/Clientes
  java ClienteUDP
  ```

---

## Fontes de Pesquisa e Referências

* **Oracle Java Documentation - Networking Basics:** [https://docs.oracle.com/javase/tutorial/networking/sockets/index.html](https://docs.oracle.com/javase/tutorial/networking/sockets/index.html)
* **Oracle Java Documentation - Class ServerSocket:** [https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html](https://docs.oracle.com/javase/8/docs/api/java/net/ServerSocket.html)
* **Oracle Java Documentation - Class DatagramSocket:** [https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html](https://docs.oracle.com/javase/8/docs/api/java/net/DatagramSocket.html)
* **Oracle Java Documentation - Concurrency & Threads:** [https://docs.oracle.com/javase/tutorial/essential/concurrency/](https://docs.oracle.com/javase/tutorial/essential/concurrency/)
