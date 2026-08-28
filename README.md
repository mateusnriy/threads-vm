# Trabalho de Sistemas Distribuídos - Programação com Threads em Java & IaaS

Repositório com a resolução da atividade prática da disciplina de **Sistemas Distribuídos**, contemplando a criação e configuração de uma **Máquina Virtual em Nuvem (IaaS)** e a implementação, execução e análise de programas concorrentes com **Threads em Java**.

---

## Informações do Trabalho

* **Disciplina:** Sistemas Distribuídos
* **Atividade:** Trabalho (1) - Threads em Java & Computação em Nuvem (IaaS)
* **Discentes:**
  * **Kaio França** (Resolução/Explicação das Questões 01, 03 e 05)
  * **Mateus Neri** (Resolução/Explicação das Questões 02, 04 e 06)

---

## Vídeo de Apresentação e Demonstração

O vídeo demonstrativo com o passo a passo da criação da Máquina Virtual na nuvem e a execução/explicação detalhada de todas as questões está documentado em:

👉 **[Acessar Documentação do Vídeo (`video/README.md`)](./video/README.md)**

* **Arquivo no repositório:** [`video/explicacao-questoes-threads.mp4`](./video/explicacao-questoes-threads.mp4) *(Gerenciado via Git LFS)*
* **Links para Streaming Online:** Disponíveis no [README da pasta de vídeo](./video/README.md).

---

## Estrutura do Repositório

O projeto está organizado em diretórios modulares para cada questão e para a gravação da atividade:

```text
threads-vm/
├── .gitattributes             # Configuração do Git LFS para arquivos binários (*.mp4)
├── .gitignore                 # Filtro para binários compilados (.class) e arquivos de IDE
├── README.md                  # Documentação principal do projeto
├── questoes/
│   ├── questao01/             # Herança de Thread e ciclo de vida
│   │   ├── MinhaThread.java
│   │   └── RESPOSTAS.md
│   ├── questao02/             # Interface Runnable e separação tarefa/execução
│   │   ├── Contador.java
│   │   └── RESPOSTAS.md
│   ├── questao03/             # Concorrência e não-determinismo do escalonador
│   │   ├── ExemploThreads.java
│   │   └── RESPOSTAS.md
│   ├── questao04/             # Ordem de execução entre threads e thread main
│   │   ├── Principal.java
│   │   └── RESPOSTAS.md
│   ├── questao05/             # Thread.sleep(), suspensão e estado TIMED_WAITING
│   │   ├── Principal.java
│   │   └── RESPOSTAS.md
│   └── questao06/             # Thread.join() e sincronização de término
│       ├── Principal.java
│       └── RESPOSTAS.md
└── video/
    ├── README.md              # Detalhamento do vídeo, minutagem e instruções de LFS
    └── explicacao-questoes-threads.mp4
```

---

## Resumo das Questões

| Questão | Conceito Principal | Arquivo Java | Respostas Teóricas |
| :---: | :--- | :--- | :--- |
| **01** | Herança da classe `Thread`, sobrescrita de `run()` e diferença entre `start()` e `run()`. | [`MinhaThread.java`](./questoes/questao01/MinhaThread.java) | [`RESPOSTAS.md`](./questoes/questao01/RESPOSTAS.md) |
| **02** | Implementação da interface `Runnable`, vantagens frente à herança e separação tarefa vs execução. | [`Contador.java`](./questoes/questao02/Contador.java) | [`RESPOSTAS.md`](./questoes/questao02/RESPOSTAS.md) |
| **03** | Execução concorrente de múltiplas threads e não-determinismo do escalonador de CPU. | [`ExemploThreads.java`](./questoes/questao03/ExemploThreads.java) | [`RESPOSTAS.md`](./questoes/questao03/RESPOSTAS.md) |
| **04** | Análise de permutações de saídas válidas em execuções concorrentes assíncronas. | [`Principal.java`](./questoes/questao04/Principal.java) | [`RESPOSTAS.md`](./questoes/questao04/RESPOSTAS.md) |
| **05** | Temporização com `Thread.sleep()`, precisão de temporização e estado `TIMED_WAITING`. | [`Principal.java`](./questoes/questao05/Principal.java) | [`RESPOSTAS.md`](./questoes/questao05/RESPOSTAS.md) |
| **06** | Sincronização e coordenação de threads com `Thread.join()`. | [`Principal.java`](./questoes/questao06/Principal.java) | [`RESPOSTAS.md`](./questoes/questao06/RESPOSTAS.md) |

---

## Como Compilar e Executar os Códigos

### Pré-requisitos
* **Java Development Kit (JDK):** Versão 8 ou superior instalada (tanto no ambiente local quanto na Máquina Virtual).
* **Git & Git LFS:** Para clonagem completa com os arquivos de mídia.

### Execução Individual de cada Questão:

```bash
# Questão 01
cd questoes/questao01
javac MinhaThread.java
java MinhaThread

# Questão 02
cd ../questao02
javac Contador.java
java Contador

# Questão 03
cd ../questao03
javac ExemploThreads.java
java ExemploThreads

# Questão 04
cd ../questao04
javac Principal.java
java Principal

# Questão 05
cd ../questao05
javac Principal.java
java Principal

# Questão 06
cd ../questao06
javac Principal.java
java Principal
```

---

## Suporte a Arquivos Grandes (Git LFS)

O repositório utiliza o **[Git Large File Storage (LFS)](https://git-lfs.github.com/)** para versionar o arquivo de vídeo sem comprometer a performance do git.

Para clonar baixando os arquivos binários:
```bash
git lfs install
git clone git@github.com:mateusnriy/threads-vm.git
```

---

## Referências

* **Oracle Java SE Documentation - Class Thread:** [https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
* **Oracle Java SE Documentation - Interface Runnable:** [https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html](https://docs.oracle.com/javase/8/docs/api/java/lang/Runnable.html)
* **Oracle Java SE Documentation - Thread.State:** [https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.State.html](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.State.html)
