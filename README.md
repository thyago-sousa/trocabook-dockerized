# Projeto Trocabook (Microsserviços com Docker Compose)

## Sobre o Projeto Trocabook

**Trocabook** é uma plataforma desenvolvida para facilitar a **venda e troca de livros usados**. O principal objetivo é promover a **reutilização** de livros, tornando a leitura mais acessível e contribuindo para um consumo mais consciente de recursos.

### Tecnologias e Ferramentas Utilizadas

* **Back-end:** Java com Spring Boot (Serviços: Principal, Admin, Chat)
* **Banco de Dados:** MySQL, MongoDB
* **Front-end:** HTML, CSS, JavaScript (com Thymeleaf no serviço principal)
* **Orquestração (Dev):** Docker Compose
* **Controle de Versão:** Git
* **Design e Prototipagem:** Figma
* **Testes de Segurança:** OWASP ZAP

---

## Como Rodar o Projeto (Usando seus Bancos Locais)

Esta configuração utiliza Docker Compose para rodar os 3 serviços Java, conectando-os aos seus bancos de dados MySQL e MongoDB locais.

### 1. Pré-requisitos (Instalar e Verificar)

**É essencial ter todos os programas abaixo instalados E RODANDO na sua máquina:**

* **Docker Desktop:** Indispensável para o Docker Compose.
    * [Download Docker Desktop](https://www.docker.com/products/docker-desktop/)
* **MySQL Server:** **LIGADO** e acessível na porta `3306`.
* **MongoDB Community Server:** **LIGADO** e acessível na porta `27017`.
* **Java JDK:** Versão 21 ou 23 (verifique os `pom.xml` dos projetos).
* **Apache Maven:** Para compilar os projetos.
* **Git:** Para clonar o repositório.

### 2. Configuração do Ambiente

**Passo 2.1: Obtenha o Projeto**

* Clone este repositório:
    ```bash
    git clone URL_DO_REPOSITORIO
    cd nome-da-pasta-do-projeto
    ```
    *Ou descompacte o arquivo `.zip` enviado.*

**Passo 2.2: Crie os Bancos de Dados (Crítico!)**

1.  Conecte-se ao seu MySQL Server local.
2.  Execute os seguintes comandos SQL (caso os bancos ainda não existam):
    ```sql
    CREATE DATABASE IF NOT EXISTS trocabook;
    CREATE DATABASE IF NOT EXISTS trocabookAdmin;
    ```
3.  *Para o MongoDB, o banco `chatdb` geralmente é criado automaticamente na primeira conexão.*

**Passo 2.3: Configure as Variáveis de Ambiente (Crítico!)**

1.  Na pasta raiz do projeto (`projeto-trocabook`), localize o arquivo `.env.example`.
2.  Crie uma **cópia** deste arquivo e renomeie-a para `.env`.
3.  **Abra o arquivo `.env`** e edite-o:
    * **`DB_PASSWORD`**: **Substitua** pelo valor correto da senha do seu usuário `root` do MySQL. Se não houver senha, deixe em branco (`DB_PASSWORD=`).
    * **(Opcional)** Preencha as chaves `RECAPTCHA_...` e `SPRING_MAIL_...` se quiser que as funcionalidades de Recaptcha e envio de e-mail funcionem.

### 3. Compilando os Projetos (Gerando os .jar)

Antes de rodar o Docker, precisamos compilar cada serviço Java.

1.  Abra seu Terminal (CMD, PowerShell, Bash, etc.) e navegue até a pasta raiz `projeto-trocabook`.
2.  Execute os comandos abaixo, **um de cada vez**, aguardando o `[INFO] BUILD SUCCESS` após cada um:

    ```bash
    # Compilar Trocabook (Principal)
    cd Trocabook
    mvn clean package -DskipTests
    cd ..

    # Compilar TrocabookAdminService
    cd TrocabookAdminService
    mvn clean package -DskipTests
    cd ..

    # Compilar TrocabookChatService
    cd TrocabookChatService
    mvn clean package -DskipTests
    cd ..
    ```

### 4. Rodando Tudo com Docker Compose!

Com tudo configurado e compilado:

1.  Certifique-se que o **Docker Desktop** está rodando.
2.  Certifique-se que seu **MySQL local** está rodando.
3.  Certifique-se que seu **MongoDB local** está rodando.
4.  No seu terminal (na pasta raiz `projeto-trocabook`), execute:
    ```bash
    docker-compose up --build
    ```
5.  Aguarde o Docker construir as imagens e iniciar os 3 containers. Você verá os logs dos serviços no terminal.

### 5. Testando a Aplicação

Quando os logs indicarem que os três serviços iniciaram (procure por mensagens como `Started TrocabookApplication...`), você pode acessar:

* **Aplicação Principal:** [http://localhost:8080](http://localhost:8080)
* **Página do Admin (Dashboard):** [http://localhost:8080/admin/dashboard-pagina](http://localhost:8080/admin/dashboard-pagina)
* **Página do Chat (Lista):** [http://localhost:8080/chat/list-mensagens](http://localhost:8080/chat/list-mensagens)

**Importante:** Tentar acessar `http://localhost:8181` ou `http://localhost:8282` diretamente resultará em um erro `404 Not Found`. Isso é **normal**, pois esses são apenas os backends (APIs). As páginas visuais são todas servidas pelo serviço principal na porta `8080`.

### 6. Parando os Serviços

Para parar todos os containers, volte ao terminal onde o `docker-compose up` está rodando e pressione `Ctrl + C`. Depois, para remover os containers e a rede, execute:

```bash
docker-compose down
