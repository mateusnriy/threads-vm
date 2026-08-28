# Questão 03 - Resposta

Pergunta: A ordem de impressão será sempre a mesma? Justifique.

- Não. A ordem de execução depende do escalonador de threads do Sistema Operacional e da JVM, que é não-determinístico. Como as threads executam concorrentemente e não há mecanismos de sincronização definindo uma ordem, a sequência de impressão no console pode mudar a cada execução.
