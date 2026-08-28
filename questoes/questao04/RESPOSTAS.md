# Questão 04 - Respostas

a) Quais das alternativas abaixo podem representar uma saída válida?
I. A B C
II. C A B
III. B A C
IV. A C B
- **Todas as alternativas (I, II, III e IV)**. Como as threads `t1`, `t2` e a thread `main` executam de forma concorrente e assíncrona, qualquer uma dessas ordens pode ocorrer.

b) Existe uma ordem obrigatória entre A, B e C? Por que?
- **Não.** As mensagens são impressas por três threads concorrentes sem mecanismos de sincronização. A ordem de exibição depende exclusivamente do escalonador de threads do Sistema Operacional/JVM.
