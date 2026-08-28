# Vídeo de Demonstração e Explicação das Questões

Este diretório contém a gravação da atividade prática referente ao **Trabalho (1) de Sistemas Distribuídos**, demonstrando a criação da Máquina Virtual em ambiente de nuvem (IaaS) e a execução/explicação dos códigos com Threads em Java.

---

## Informações da Atividade

* **Disciplina:** Sistemas Distribuídos
* **Trabalho:** Trabalho (1) - Programação com Threads e Computação em Nuvem (IaaS)
* **Discentes:**
  * Kaio França
  * Mateus Neri

---

## Conteúdo do Vídeo

O vídeo contempla todos os requisitos solicitados na atividade:
1. **Provisionamento da Máquina Virtual (IaaS):** Passo a passo da criação e configuração da instância de Máquina Virtual em nuvem gratuita.
2. **Ambiente e Execução:** Transferência, compilação e execução dos programas Java na VM.
3. **Explicação Técnica das Questões:** Análise detalhada do código e respostas teóricas, com divisão equilibrada entre os membros da dupla:
   * **Questões 01, 03 e 05:** Explicadas por Kaio França.
   * **Questões 02, 04 e 06:** Explicadas por Mateus Neri.

---

## Tabela de Conteúdo e Questões Abordadas

| Etapa / Questão | Responsável | Tópicos Principais Abordados |
| :--- | :--- | :--- |
| **Criação da VM (IaaS)** | Dupla | Provisionamento da instância na nuvem, acesso SSH e setup do Java (JDK). |
| **Questão 01** | Kaio França | Herança de `Thread`, sobrescrita de `run()`, inicialização com `start()` vs chamada direta de `run()`. |
| **Questão 02** | Mateus Neri | Interface `Runnable`, separação de tarefa e execução, contagem concorrente. |
| **Questão 03** | Kaio França | Execução simultânea de 3 threads, concorrência e não-determinismo do escalonador. |
| **Questão 04** | Mateus Neri | Análise de saídas válidas de threads concorrentes e ausência de ordem obrigatória. |
| **Questão 05** | Kaio França | Uso de `Thread.sleep()`, suspensão temporizada, precisão do tempo e estado `TIMED_WAITING`. |
| **Questão 06** | Mateus Neri | Método `join()`, sincronização de fluxo, bloqueio da thread `main` e impacto de sua remoção. |

---

## Como Assistir ao Vídeo

### 1. Visualização Online (Recomendado)
Para facilitar a visualização sem necessidade de baixar o repositório completo:
* **YouTube (Não Listado):** `[Insira o link do YouTube aqui se disponível]`
* **Google Drive / Nuvem:** `[Insira o link do Drive/OneDrive aqui se disponível]`

---

### 2. Arquivo Local no Repositório (`explicacao-questoes-threads.mp4`)
O arquivo de vídeo original está presente nesta pasta:
* **Caminho:** `video/explicacao-questoes-threads.mp4`
* **Formato:** MP4 (Vídeo H.264 / Áudio AAC)
* **Tamanho:** ~108 MB

> [!IMPORTANT]
> O arquivo de vídeo possui mais de 100 MB e é gerenciado através do **Git LFS (Large File Storage)**.

#### Como baixar o vídeo ao clonar o repositório:
Certifique-se de ter o [Git LFS](https://git-lfs.github.com/) instalado no seu sistema:

```bash
# 1. Instalar e inicializar o Git LFS (caso ainda não tenha feito)
git lfs install

# 2. Clonar o repositório baixando os arquivos binários LFS
git clone <URL_DO_REPOSITORIO>

# Caso já tenha clonado sem o LFS, baixe o arquivo com:
git lfs pull
```
