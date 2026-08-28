# Questão 03 - Resposta

### Enunciado
Crie três threads: `Thread A`, `Thread B` e `Thread C`. Cada thread deverá imprimir seu nome cinco vezes. Execute as três threads simultaneamente.

---

### Pergunta e Resposta

#### A ordem de impressão será sempre a mesma? Justifique.
* **Resposta:** **Não.** A ordem de execução e impressão não é determinística. Quando múltiplas threads são iniciadas simultaneamente sem mecanismos explícitos de sincronização, a alternância e a fatia de tempo de CPU (*time slicing*) concedida a cada uma dependem exclusivamente do **escalonador de threads do Sistema Operacional e da JVM**. Dessa forma, a cada execução, a sequência de impressões pode intercalar de maneiras diferentes.
