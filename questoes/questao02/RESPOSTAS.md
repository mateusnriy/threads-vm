# Questão 02 - Respostas

### Enunciado
Crie uma classe `Contador` que implemente `Runnable`. A thread deverá imprimir os números de 1 a 10, um número por linha.

---

### Perguntas e Respostas

#### a) Qual é a vantagem de utilizar `Runnable` em relação à herança de `Thread`?
* **Resposta:** 
  1. **Flexibilidade de Herança:** Como a linguagem Java suporta apenas herança simples de classes (mas permite múltiplas interfaces), implementar `Runnable` permite que a classe herde de outra classe base caso necessário.
  2. **Separação de Responsabilidades (Boas Práticas):** Separa a definição do trabalho a ser executado (`Runnable` - a tarefa) do mecanismo de gerenciamento e ciclo de vida da execução (`Thread` - o executor). Isso facilita o reuso de código e a integração com executores modernos (`ExecutorService`, pools de threads).

#### b) Qual objeto representa a tarefa?
* **Resposta:** O objeto da classe `Contador` (a instância que implementa a interface `Runnable`). Ele encapsula o método `run()` com a lógica da contagem.

#### c) Qual objeto representa a execução da tarefa?
* **Resposta:** O objeto da classe `Thread` (a instância de `java.lang.Thread` que recebe o `Runnable` no construtor). É ele quem interage com o Sistema Operacional/JVM para instanciar a thread nativa, alocar recursos e gerenciar a execução através do método `start()`.