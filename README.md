# Sistemas Distribuídos - Repositório Central de Trabalhos Práticos

Este repositório centraliza os trabalhos, projetos e atividades práticas desenvolvidos para a disciplina de **Sistemas Distribuídos**. O objetivo principal é consolidar implementações de programação concorrente, comunicação em rede via sockets, arquiteturas cliente-servidor e execução em ambientes de infraestrutura distribuída e nuvem (IaaS / Máquinas Virtuais).

---

## 👥 Discentes

* **Kaio França**
* **Mateus Neri**

---

## 📚 Índice de Trabalhos

| Trabalho | Descrição | Diretório | Vídeo de Apresentação |
| :--- | :--- | :---: | :---: |
| **01. Programação com Threads em Java & Nuvem (IaaS)** | Estudo aprofundado de concorrência, criação de threads, ciclo de vida, estados e sincronização em Java, com deploy em VM na nuvem. | [Acessar](./trabalho-threads-vm/) | [▶️ Assistir no YouTube](https://www.youtube.com/watch?v=2Xp9baBAj30) |
| **02. Aplicação Cliente-Servidor Concorrente em VMs (TCP e UDP)** | Aplicação distribuída com servidores multithreaded TCP e UDP para processamento concorrente e telemetria entre múltiplas VMs. | [Acessar](./trabalho-vms-aplicacao/) | [▶️ Assistir no YouTube](https://www.youtube.com/watch?v=h8lpCKpLrb8) |
| **03. Aplicação RPC Síncrona e Assíncrona Multithread em VMs** | Aplicação distribuída em Java RMI cobrindo chamadas remotas síncronas bloqueantes, assíncronas com Futures e concorrência multithread no servidor em 3 VMs. | [Acessar](./trabalho-vms-rpc/) | [▶️ Assistir ao vídeo](https://drive.google.com/drive/folders/1cjk7zGH5zkfAC9y20gmzN5gD1Hf_X6CF?usp=sharing) |

---

## 📂 Visão Geral dos Trabalhos

### 🧵 [Trabalho 01: Programação com Threads em Java](./trabalho-threads-vm/)
* **Foco:** Concorrência e Multithreading na JVM.
* **Conteúdo:** 6 questões práticas cobrindo instanciação (`Thread` vs `Runnable`), estados de threads, contadores compartilhados e condições de corrida (`Race Conditions`), sincronização e escalonamento.
* **Infraestrutura:** Instanciação e configuração de Máquina Virtual na nuvem (IaaS) para execução dos códigos.
* **Documentação Detalhada:** Consulte o [README do Trabalho 01](./trabalho-threads-vm/README.md).

### 🌐 [Trabalho 02: Aplicação Distribuída Cliente-Servidor (TCP e UDP)](./trabalho-vms-aplicacao/)
* **Foco:** Comunicação em rede, arquitetura Cliente-Servidor e concorrência distribuída.
* **Conteúdo:**
  * **Servidor e Cliente TCP:** Comunicação orientada a conexão na porta `5000` com atendimento concorrente por thread dedicada (`Atendente`) e simulação de processamento de texto.
  * **Servidor e Cliente UDP:** Envio de telemetria/status de máquina via datagramas na porta `5001`, com workers assíncronos e controle de confirmação com timeout (**ACK**).
* **Infraestrutura:** Configuração de rede distribuída entre múltiplas Máquinas Virtuais (VMs) na nuvem/rede local.
* **Documentação Detalhada:** Consulte o [README do Trabalho 02](./trabalho-vms-aplicacao/README.md).

### ⚡ [Trabalho 03: Aplicação RPC Síncrona e Assíncrona Multithread em VMs](./trabalho-vms-rpc/)
* **Foco:** Chamada de Procedimento Remoto (RPC), Java RMI, Multithreading no servidor e Futures no cliente.
* **Conteúdo:**
  * **Serviços Remotos:** Operações de cálculo simples, processamento demorado artificialmente controlado e processamento de coleção agregador.
  * **Parte I (Síncrona):** Cliente com espera bloqueante de I/O e log de tempos de resposta.
  * **Parte II (Assíncrona):** Cliente não-bloqueante executando tarefas locais paralelas com `CompletableFuture`.
  * **Concorrência Simultânea:** Execução simultânea a partir da VM2 (10s) e VM3 (5s) demonstrando processamento multithread do servidor.
* **Infraestrutura:** Mesma topologia corporativa de 3 VMs (VM1 Gateway/DHCP/Servidor, VM2 Cliente 1, VM3 Cliente 2).
* **Documentação Detalhada:** Consulte o [README do Trabalho 03](./trabalho-vms-rpc/README.md) e o [Plano de Desenvolvimento da Dupla](./trabalho-vms-rpc/PLANO_DESENVOLVIMENTO.md).

---

## 🗂️ Estrutura do Repositório

```text
threads-vm/
├── trabalho-threads-vm/             # Trabalho 01 - Programação com Threads em Java
│   ├── questao01/                   # Implementação de Threads e ciclo de vida
│   ├── questao02/                   # Compartilhamento de recursos e concorrência
│   ├── questao03/                   # Gerenciamento de múltiplas threads
│   ├── questao04/                   # Análise de estados de execução
│   ├── questao05/                   # Sincronização e controle de fluxo
│   ├── questao06/                   # Simulação de concorrência com espera
│   └── README.md                    # Documentação do Trabalho 01
│
├── trabalho-vms-aplicacao/          # Trabalho 02 - Aplicação Distribuída em VMs
│   ├── Clientes/
│   │   ├── ClienteTCP.java          # Cliente de mensagens TCP
│   │   └── ClienteUDP.java          # Cliente de telemetria UDP
│   ├── Servidor/
│   │   ├── ServidorTCP.java         # Servidor multithreaded TCP (Porta 5000)
│   │   └── ServidorUDP.java         # Servidor multithreaded UDP (Porta 5001)
│   └── README.md                    # Documentação do Trabalho 02
│
├── trabalho-vms-rpc/                # Trabalho 03 - Aplicação RPC (Síncrono e Assíncrono)
│   ├── Clientes/
│   │   ├── ClienteSincrono.java     # Parte I: Terminal Caixa síncrono (10s)
│   │   ├── ClienteAssincrono.java   # Parte II: Terminal Caixa assíncrono com Future
│   │   ├── SimuladorConcorrencia.java # Simulação concorrente Caixa 1 vs Caixa 2
│   │   ├── ServicoRemoto.java       # Interface RMI dos contratos bancários
│   │   └── ResultadoBalancete.java  # Objeto DTO de balancete consolidado
│   ├── Servidor/
│   │   ├── ServidorRPC.java         # Publicação RMI multithread na porta 1099
│   │   ├── ServicoRemotoImpl.java   # Implementação com logs da thread atendente
│   │   ├── ServicoRemoto.java       # Interface RMI dos contratos bancários
│   │   └── ResultadoBalancete.java  # Objeto DTO de balancete consolidado
│   ├── PLANO_DESENVOLVIMENTO.md     # Plano da dupla, roteiro de vídeo e questões
│   └── README.md                    # Instruções de compilação, execução e testes
│
└── README.md                        # Documentação Geral / Repositório Central
```

---

## 🛠️ Tecnologias e Ferramentas Utilizadas

* **Linguagem:** [Java (JDK 8+)](https://www.oracle.com/java/)
* **Paradigmas Distribuídos:** RPC (Java RMI - `Remote`, `UnicastRemoteObject`, `LocateRegistry`), Sockets (`ServerSocket`, `DatagramSocket`), Java Concurrency (`CompletableFuture`, `Thread`, `Runnable`)
* **Infraestrutura:** Máquinas Virtuais (VMs), Hypervisors (VirtualBox, VMware, Hyper-V, Proxmox), Roteamento e DHCP Linux
* **Controle de Versão:** Git & GitHub

