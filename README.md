# Trabalho de Sistemas Distribuídos - Programação com Threads em Java

Este repositório contém a resolução das questões da atividade prática da disciplina de **Sistemas Distribuídos**, abordando conceitos fundamentais de concorrência, criação de threads e gerenciamento de estados em Java, além da execução em ambiente de nuvem (IaaS).

---

## Discentes

* **Kaio França**
* **Mateus Neri**

---

## Vídeo de Demonstração e Explicação

O vídeo com o passo a passo da criação da Máquina Virtual na nuvem (IaaS) e a execução/explicação dos códigos das 6 questões foi publicado no YouTube:

* ▶️ **Link do Vídeo no YouTube:** [https://youtu.be/2Xp9baBAj30](https://www.youtube.com/watch?v=2Xp9baBAj30)

### Divisão das Apresentações:
* **Criação e Configuração da VM (IaaS):** Kaio França e Mateus Neri
* **Questões 01, 03 e 05:** Explicadas por Kaio França
* **Questões 02, 04 e 06:** Explicadas por Mateus Neri

---

## Estrutura do Repositório

As soluções foram organizadas dentro do diretório `questoes/`, separadas por pastas para cada questão solicitada:

```text
questoes/
├── questao01/
│   ├── MinhaThread.java
│   └── RESPOSTAS.md
├── questao02/
│   ├── Contador.java
│   └── RESPOSTAS.md
├── questao03/
│   ├── ExemploThreads.java
│   └── RESPOSTAS.md
├── questao04/
│   ├── Principal.java
│   └── RESPOSTAS.md
├── questao05/
│   ├── Principal.java
│   └── RESPOSTAS.md
└── questao06/
    ├── Principal.java
    └── RESPOSTAS.md
```

---

## Como Compilar e Executar os Códigos

Necessário ter o JDK (Java Development Kit) 8 ou superior instalado no ambiente.

### Exemplo de execução:
```bash
cd questoes/questao01
javac MinhaThread.java
java MinhaThread
```

---

## Fonte de Pesquisa

A fundamentação das respostas teóricas e o embasamento para a implementação do código fonte em Java utilizaram como referência a documentação oficial da Oracle:

* **Oracle Java 8 Documentation - Class Thread:** [https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html](https://docs.oracle.com/javase/8/docs/api/java/lang/Thread.html)
