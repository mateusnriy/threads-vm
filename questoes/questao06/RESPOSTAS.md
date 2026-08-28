# Questão 06 - Respostas

a) O que join() faz?
- Faz com que a thread atual aguarde a conclusão da execução da thread sobre a qual o método foi chamado.

b) A mensagem "Fim" pode aparecer antes do número 5?
- **Não.** A chamada `t.join()` bloqueia a execução até que a thread `t` termine de imprimir todos os números de 1 a 5.

c) Qual thread fica esperando?
- A thread `main`.

d) Remova o join(). O comportamento pode mudar? Explique.
- **Sim.** Sem o `join()`, a thread `main` não espera `t` finalizar. Dessa forma, a mensagem "Fim" é executada concorrentemente e pode aparecer em qualquer posição da saída.
