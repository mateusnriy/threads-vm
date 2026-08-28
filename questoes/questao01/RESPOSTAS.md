# Questão 01 - Respostas

a) Qual método é responsável pela implementação da tarefa?
- O método `run()`. Ele contém o código que será executado pela thread.

b) Qual método deve ser utilizado para iniciar a thread?
- O método `start()`. Ele solicita à JVM/SO a criação de uma nova thread de execução e chama o método `run()` internamente.

c) O que aconteceria se fosse utilizado run() em vez de start()?
- O método `run()` seria executado como uma chamada de método comum na thread atual (`main`), de forma síncrona, sem criar uma nova thread de execução paralela.
