# Trabalho de Sistemas Distribuídos - Aplicação RPC Bancária (Core Banking) Síncrona e Assíncrona Multithread em VMs

Este projeto implementa uma aplicação distribuída baseada no modelo de **Chamada de Procedimento Remoto (RPC - Remote Procedure Call)**, utilizando **Java RMI (Remote Method Invocation)** nativo. 

O projeto foi contextualizado em um cenário corporativo real de **Core Banking / Sistema Financeiro Distribuído**, onde o **Servidor Central (VM1)** atende múltiplos **Terminais de Atendimento / Caixas de Agência (VM2 e VM3)** localizados em uma rede interna protegida.

---

## 👥 Discentes

* **Kaio França**
* **Mateus Neri**

---

## 📹 Vídeo de Demonstração e Explicação

O vídeo com o passo a passo da infraestrutura de rede, compilação, execução concorrente dos clientes síncrono e assíncrono e discussão das respostas avaliativas está disponível em:

* ▶️ **Link do Vídeo no YouTube:** [Adicione aqui o link do vídeo gravado]

---

## 🌐 1. Topologia da Rede e Arquitetura

O ambiente de rede reaproveita **100% da infraestrutura da Unidade I**, sem necessidade de qualquer alteração nas configurações das Máquinas Virtuais:

```text
       ┌────────────────────────┐
       │     REDE EXTERNA       │
       │  (Internet / Roteador) │
       └───────────┬────────────┘
                   │ eth0 (NAT / DHCP Externo)
       ┌───────────┴────────────────────────────────────────┐
       │     VM 1: Servidor Central Bancário / Gateway      │
       │             IP Interno: 192.168.10.10              │
       │    [Servidor RPC Core Banking Multithread Java]    │
       └───────────┬────────────────────────────────────────┘
                   │ eth1 (Rede B Interna: 192.168.10.0/24)
         ┌─────────┴─────────┐
         │  vSwitch Interno  │
         └────┬────────────┬─┘
              │            │
  ┌───────────┴──┐      ┌──┴────────────┐
  │ VM 2: Caixa 1│      │ VM 3: Caixa 2 │
  │  (Rede B)    │      │  (Rede B)     │
  │ [Cliente RPC]│      │ [Cliente RPC] │
  └──────────────┘      └───────────────┘
```

* **VM 1 (Servidor Central de Risco e Contabilidade - IP `192.168.10.10`):**
  * Roteador/NAT e Servidor DHCP para a Rede B.
  * Executa o servidor multithread Java RMI na porta `1099`.
* **VM 2 (Terminal Caixa 1 - Rede B):** Executa chamadas remotas de atendimento e auditoria.
* **VM 3 (Terminal Caixa 2 - Rede B):** Executa chamadas simultâneas permitindo comprovar a concorrência e o paralelismo no servidor.

---

## 💼 2. Operações Remotas do Core Banking

Para atender com excelência ao requisito do professor (*"a aplicação não deve ser um simples Hello World ou variantes"*), as 3 operações foram modeladas em um caso de uso financeiro:

1. **Cálculo Simples (`calcularConversaoCambio`):**
   * Recebe um valor monetário em Reais (BRL) e a cotação cambial da moeda estrangeira.
   * Processamento imediato (< 5ms) retornando o valor convertido.
2. **Processamento Demorado Controlado (`auditarTransacaoRisco`):**
   * Simula a análise profunda antifraude e risco de crédito para transações de grande porte (ex: R$ 500.000,00).
   * O tempo de análise é parametrizável (ex: 5s, 10s via `Thread.sleep`) para demonstrar o impacto de chamadas síncronas bloqueantes vs assíncronas não-bloqueantes.
3. **Processamento de Coleção (`processarBalanceteTransacoes`):**
   * Recebe um lote de transações financeiras (`double[]`) realizadas pelo caixa durante o expediente.
   * Retorna um objeto serializado (`ResultadoBalancete.java`) consolidando: quantidade de operações, volume total movimentado, ticket médio, maior transação, menor transação e tempo de processamento.

---

## 📁 3. Estrutura do Diretório

A estrutura segue o mesmo padrão limpo do trabalho anterior (`trabalho-vms-aplicacao`), sem pacotes complexos, permitindo que cada pasta seja compilada de forma independente nas VMs:

```text
trabalho-vms-rpc/
├── Clientes/
│   ├── ClienteSincrono.java         # Parte I: Caixa com espera bloqueante (10s)
│   ├── ClienteAssincrono.java       # Parte II: Caixa com Future e tarefas locais
│   ├── SimuladorConcorrencia.java   # Utilitario para simular Caixa 1 e Caixa 2 concorrentes
│   ├── ServicoRemoto.java           # Interface com as assinaturas RPC bancarias
│   └── ResultadoBalancete.java      # Objeto DTO serializavel de balancete consolidado
│
├── Servidor/
│   ├── ServidorRPC.java             # Inicializador do Servidor Multithread (Porta 1099)
│   ├── ServicoRemotoImpl.java       # Implementacao com logs da thread atendente
│   ├── ServicoRemoto.java           # Interface com as assinaturas RPC bancarias
│   └── ResultadoBalancete.java      # Objeto DTO serializavel de balancete consolidado
│
├── PLANO_DESENVOLVIMENTO.md         # Divisao da dupla, roteiro de video e perguntas
└── README.md                        # Documentacao completa do projeto
```

---

## 🚀 4. Como Compilar e Executar nas VMs

### Pré-requisitos
* **JDK 8 ou superior** instalado nas 3 VMs Linux.
* Conectividade ativa na Rede B (`ping 192.168.10.10`).

---

### Passo 1: Executar o Servidor na VM 1 (Servidor Central)

No terminal da **VM 1**:
```bash
cd trabalho-vms-rpc/Servidor
javac *.java
java ServidorRPC 192.168.10.10
```

> **Console do Servidor:**
> ```text
> =============================================================
>    CORE BANKING - SERVIDOR RPC CENTRAL MULTITHREAD (VM1)     
> =============================================================
> [*] Configurando IP de bind RMI: 192.168.10.10
> [*] Porta do Registro RMI: 1099
> [+] Registro RMI criado na porta 1099
> [+] Servico remoto 'ServicoBancarioRPC' publicado com sucesso!
> [+] Servidor pronto para atender chamadas simultaneas dos caixas (VM2 e VM3).
> [*] Aguardando requisicoes dos terminais...
> ```

---

### Passo 2: Executar a Parte I (RPC Síncrono) na VM 2

No terminal da **VM 2**:
```bash
cd trabalho-vms-rpc/Clientes
javac *.java
# Parametros: [IP_SERVIDOR] [NOME_TERMINAL] [SEGUNDOS_AUDITORIA]
java ClienteSincrono 192.168.10.10 "CAIXA 1" 10
```

> **Comportamento observado:** O terminal fica **completamente congelado por 10 segundos** aguardando a auditoria de risco da central, demonstrando com clareza o bloqueio de thread:
> ```text
> CAIXA 1
> 10:15:01 - Enviando requisicao RPC
> 10:15:01 - Operacao solicitada: auditarTransacaoRisco(CAIXA 1, R$ 500.000, 10s)
> 10:15:11 - Resposta recebida
> 10:15:11 - Detalhes do parecer: PARECER: APROVADO [Transacao R$ 500000,00 auditada com sucesso pelo Thread [RMI TCP Connection(1)...] em 10s para CAIXA 1]
> 10:15:11 - Tempo total: 10 segundos
> ```

---

### Passo 3: Executar a Parte II (RPC Assíncrono) na VM 2 ou VM 3

No terminal da **VM 2**:
```bash
cd trabalho-vms-rpc/Clientes
java ClienteAssincrono 192.168.10.10 "CAIXA 1 (Assincrono)" 10
```

> **Comportamento observado:** A requisição é disparada com `CompletableFuture`. A thread do caixa **continua livre** executando tarefas locais da agência sem congelar a interface:
> ```text
> INICIO
> 10:20:01 - Requisicao assincrona enviada (Auditoria de Risco de R$ 500.000 - 10s)
> 10:20:01 - Cliente continuou sua execucao
> 10:20:03 - Executando atividade local 1: Emissao de comprovante fiscal e autenticacao mecanica local
> 10:20:05 - Executando atividade local 2: Conciliacao de saldo fisico da gaveta de dinheiro
> 10:20:07 - Executando atividade local 3: Registro de log de auditoria no disco local da agencia
> 10:20:09 - Executando atividade local 4: Verificacao de limites operacionais do caixa local
> 10:20:11 - Executando atividade local 5: Preparacao de interface para o proximo cliente da fila
> 10:20:11 - Resultado RPC disponivel:
> 10:20:11 - PARECER: APROVADO [Transacao R$ 500000,00 auditada com sucesso pelo Thread [RMI TCP Connection(2)...] em 10s para CAIXA 1 (Assincrono)]
> FIM
> ```

---

### Passo 4: Demonstração de Concorrência Multithread (VM 2 e VM 3 Simultâneas)

1. No terminal da **VM 2** (solicita auditoria pesada de 10s):
   ```bash
   cd trabalho-vms-rpc/Clientes
   java ClienteSincrono 192.168.10.10 "Caixa-VM2" 10
   ```
2. Imediatamente no terminal da **VM 3** (solicita auditoria rápida de 5s):
   ```bash
   cd trabalho-vms-rpc/Clientes
   java ClienteSincrono 192.168.10.10 "Caixa-VM3" 5
   ```

* **Resultado no console do Servidor (VM 1):** O servidor mostra duas threads distintas atendendo ao mesmo tempo.
* **Resultado nos Clientes:** A **VM 3 conclui em 5 segundos**, antes da VM 2 terminar os seus 10 segundos. Isso comprova que o servidor multithread processa de forma concorrente sem enfileiramento bloqueante (*Head-of-Line Blocking*).

---

## 📊 5. Perguntas Avaliativas Respondidas

1. **Qual foi a principal diferença observada entre RPC síncrona e RPC assíncrona?**
   * Na RPC síncrona, a thread do caixa é estritamente bloqueada no envio da requisição até a chegada da resposta. Na RPC assíncrona, a chamada é delegada em segundo plano retornando imediatamente um `Future`, permitindo que o caixa continue trabalhando em tarefas locais.
2. **O que aconteceu com o Cliente 1 enquanto aguardava uma chamada síncrona de 10 segundos?**
   * Ficou congelado por 10 segundos em estado de espera passiva de I/O, incapaz de executar instruções locais.
3. **O que o cliente conseguiu realizar durante uma chamada assíncrona?**
   * Realizou 5 atividades locais em paralelo (emissão de comprovante fiscal, conciliação de gaveta, verificação de limites, etc.) enquanto a auditoria de risco ocorria na VM1.
4. **Qual é a vantagem de utilizar um servidor multithread neste cenário?**
   * Permite que múltiplos caixas bancários (VM 2 e VM 3) sejam atendidos de forma independente e simultânea, evitando que uma auditoria longa em uma agência trave os outros caixas.
5. **O que aconteceria se o servidor possuísse apenas uma thread e recebesse simultaneamente uma operação de 10 segundos e outra de 5 segundos?**
   * Ocorreria bloqueio por cabeça de fila (*Head-of-Line Blocking*): o Caixa 2 esperaria os 10s do Caixa 1 terminarem e demoraria 15 segundos no total para ter sua resposta de 5s.
6. **RPC assíncrona necessariamente torna o processamento realizado pelo servidor mais rápido? Justifique.**
   * Não. O custo computacional do algoritmo no servidor é exatamente o mesmo. O ganho é no cliente, que ganha fluidez e não desperdiça tempo ocioso.
7. **Qual é a diferença entre assincronicidade no cliente e concorrência no servidor?**
   * Assincronicidade no cliente é a capacidade do emissor não bloquear seu fluxo local enquanto aguarda I/O de rede. Concorrência no servidor é a capacidade do receptor executar múltiplos fluxos de trabalho (threads) simultaneamente.
8. **As duas chamadas RPC foram realmente executadas simultaneamente ou concorrentemente? Explique considerando o ambiente utilizado.**
   * Foram concorrentes pelo escalonador do SO. Se a VM1 tiver 2 ou mais vCPUs em um host com múltiplos núcleos físicos, houve simultaneidade (paralelismo real); caso possua apenas 1 vCPU, a execução foi concorrente via preempção temporal (*time-slicing*).
