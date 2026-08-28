# Questão 05 - Respostas

### Código Analisado
```java
public class Principal {
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 3; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        });
        t.start();
    }
}
```

---

### Perguntas e Respostas

#### a) O que faz `Thread.sleep(1000)`?
* **Resposta:** Coloca a thread em execução atual em estado de suspensão temporária pelo período especificado em milissegundos (no caso, 1000 ms ou 1 segundo), liberando o processador para a execução de outras threads e tarefas durante esse intervalo.

#### b) Quantas vezes a thread será suspensa?
* **Resposta:** A thread será suspensa **3 vezes** (uma suspensão a cada iteração do laço `for`, para os valores $i = 1, 2, 3$).

#### c) O programa necessariamente levará exatamente 3 segundos?
* **Resposta:** **Não.** O tempo total será de **no mínimo aproximadamente 3 segundos**, mas normalmente leva ligeiramente mais. Isso ocorre devido à latência de chaveamento de contexto, tempo de execução das instruções de código, precisão do temporizador do Sistema Operacional e disponibilidade da CPU no instante em que a thread acorda e volta para a fila de prontos.

#### d) Qual estado da thread está relacionado ao período de espera temporizada?
* **Resposta:** O estado `TIMED_WAITING` (Espera Temporizada), conforme definido na enumeração `java.lang.Thread.State`.
