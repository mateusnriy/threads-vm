# Questão 05 - Respostas

a) O que faz Thread.sleep(1000)?
- Pausa a execução da thread atual por 1000 milissegundos (1 segundo), liberando o uso do processador durante esse período.

b) Quantas vezes a thread será suspensa?
- 3 vezes (uma vez a cada iteração do laço `for`).

c) O programa necessariamente levará exatamente 3 segundos?
- Não. Levará no mínimo cerca de 3 segundos, pois existem pequenos atrasos provocados pelo tempo de execução das instruções, inicialização da JVM e a fila do escalonador do SO para reativar a thread.

d) Qual estado da thread está relacionado ao período de espera temporizada?
- O estado `TIMED_WAITING`.
