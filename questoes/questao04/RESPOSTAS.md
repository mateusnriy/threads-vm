# Questão 04 - Respostas

### Código Analisado
```java
public class Principal {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            System.out.println("A");
        });
        Thread t2 = new Thread(() -> {
            System.out.println("B");
        });

        t1.start();
        t2.start();

        System.out.println("C");
    }
}
```

---

### Perguntas e Respostas

#### a) Quais das alternativas abaixo podem representar uma saída válida?
* **I.** A B C
* **II.** C A B
* **III.** B A C
* **IV.** A C B

* **Resposta:** **Todas as alternativas (I, II, III e IV)** representam saídas válidas. Existem três fluxos concorrentes em execução: a thread `t1` (que imprime "A"), a thread `t2` (que imprime "B") e a thread `main` (que imprime "C"). Qualquer intercalação entre essas três impressões pode ocorrer.

#### b) Existe uma ordem obrigatória entre A, B e C? Por que?
* **Resposta:** **Não existe ordem obrigatória.** Como `t1`, `t2` e a thread `main` executam de forma assíncrona e independente, sem barreiras de sincronização, semáforos ou `join()`, a ordem de impressão no console depende unicamente de qual thread obtém tempo de processamento primeiro junto ao escalonador do Sistema Operacional.
