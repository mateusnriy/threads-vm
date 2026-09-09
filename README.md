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
└── README.md                        # Documentação Geral / Repositório Central
```

---

## 🛠️ Tecnologias e Ferramentas Utilizadas

* **Linguagem:** [Java (JDK 8+)](https://www.oracle.com/java/)
* **Redes & Concorrência:** Java Sockets (`ServerSocket`, `Socket`, `DatagramSocket`, `DatagramPacket`), Java Threads (`Thread`, `Runnable`)
* **Infraestrutura:** Máquinas Virtuais (VMs), Cloud IaaS, Redes Locais Virtuais
* **Controle de Versão:** Git & GitHub
