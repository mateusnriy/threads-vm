# Questão 02 - Respostas

a) Qual é a vantagem de utilizar Runnable em relação à herança de Thread?
- Permite que a classe herde de outra classe base, pois Java não possui herança múltipla, e separa a definição da tarefa (`Runnable`) do controle de execução (`Thread`).

b) Qual objeto representa a tarefa?
- O objeto da classe `Contador` (instância que implementa a interface `Runnable`). Ele contém o método `run()`, onde está definida a lógica da tarefa que deve ser executada.

c) Qual objeto representa a execução da tarefa?
- O objeto da classe `Thread` (instância de `java.lang.Thread` que recebe o `Runnable` em seu construtor). É ele que interage com a JVM e o Sistema Operacional para criar um novo fluxo de execução (thread nativa), alocar recursos e gerenciar o ciclo de vida da execução através do método `start()`.