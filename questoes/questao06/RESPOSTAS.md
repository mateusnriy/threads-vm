# Questão 06 - Respostas

### Código Analisado
```java
public class Principal {
    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                System.out.println(i);
            }
        });

        t.start();
        t.join();

        System.out.println("Fim");
    }
}
```

---

### Perguntas e Respostas

#### a) O que `join()` faz?
* **Resposta:** Faz com que a thread invocadora (neste caso, a thread `main`) bloqueie sua execução e aguarde até que a thread alvo (`t`) finalize sua execução por completo.

#### b) A mensagem "Fim" pode aparecer antes do número 5?
* **Resposta:** **Não.** Porque a instrução `System.out.println("Fim")` está posicionada estritamente após a chamada `t.join()`. Como o `join()` garante o bloqueio até a conclusão de todo o laço `for (1..5)` na thread `t`, a impressão "Fim" só será executada após todos os números de 1 a 5 terem sido exibidos.

#### c) Qual thread fica esperando?
* **Resposta:** A thread `main` (a thread principal da aplicação que invocou o método `t.join()`).

#### d) Remova o `join()`. O comportamento pode mudar? Explique.
* **Resposta:** **Sim, o comportamento muda.** Sem o `join()`, a thread `main` continuará sua execução concorrentemente com a thread `t` logo após o `t.start()`. Dessa forma, a mensagem `"Fim"` não esperará a conclusão da contagem e poderá ser impressa em qualquer momento (antes do 1, entre os números de 1 a 5, ou após o 5), dependendo do escalonamento do processador.
