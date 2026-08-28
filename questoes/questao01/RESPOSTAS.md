# Questão 01 - Respostas

### Enunciado
Crie um programa Java que:
* Crie uma classe `MinhaThread` que herde de `Thread`;
* Sobrescreva o método `run()`;
* Faça a thread imprimir: `Thread em execução!`;
* No método `main()`, crie e inicie a thread.

---

### Perguntas e Respostas

#### a) Qual método é responsável pela implementação da tarefa?
* **Resposta:** O método `run()`. Ele contém o bloco de código e a lógica que definem o trabalho a ser executado pela thread quando estiver ativa.

#### b) Qual método deve ser utilizado para iniciar a thread?
* **Resposta:** O método `start()`. Ele solicita à Máquina Virtual Java (JVM) e ao Sistema Operacional a criação de uma nova thread de execução nativa, registrando-a no escalonador e disparando internamente a execução do método `run()`.

#### c) O que aconteceria se fosse utilizado `run()` em vez de `start()`?
* **Resposta:** O método `run()` seria executado como uma chamada de método sequencial comum diretamente na thread chamadora (`main`), de forma estritamente síncrona. Ou seja, **nenhuma nova thread seria criada**, perdendo o comportamento concorrente/paralelo.
