# VemProFut! API (Concluída)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)

API base de todo o projeto VemProFut! 

## Organização das pastas:

```
    VemProFutApi/
    ├── src/                  → Código-fonte principal da aplicação
    │    ├── main/java/...    → Classes Java (controllers, services, repositories, models)
    │    ├── main/resources   → Configurações (application.properties, templates, static)
    │    └── test/java/...    → Testes automatizados
    │
    ├── mysql-init/           → Scripts de inicialização do banco MySQL
    │    └── *.sql            → Criação de tabelas, inserts iniciais
    │
    ├── .mvn/wrapper/         → Arquivos do Maven Wrapper (executar sem instalar Maven)
    │
    ├── Dockerfile            → Configuração para criar imagem Docker da API
    ├── Docker-compose.yml    → Orquestração de containers (API + MySQL)
    │
    ├── pom.xml               → Arquivo de configuração do Maven (dependências e build)
    ├── mvnw / mvnw.cmd       → Scripts para rodar Maven Wrapper (Linux/Windows)
    │
    ├── .env.exemple          → Exemplo de variáveis de ambiente (configuração DB, etc.)
    ├── .gitignore            → Arquivos/pastas ignorados pelo Git
    ├── .gitattributes        → Configurações de atributos do Git
    ├── excludeFilter.xml     → Configuração de exclusões (provavelmente Sonar ou Checkstyle)
    └── README.md             → Documentação inicial da API
```

# 🚀 Como rodar a aplicação

A aplicação suporta múltiplos perfis (`dev`, `test`, `prod`) configurados via **Spring Profiles**.

---
## 🏭 Ambiente de Produção (`prod`)

Rodar com **Docker Compose**:
```bash
docker compose up
```
## 🔧 Ambiente de Desenvolvimento (`dev`)

Rodar diretamente com **Maven**:
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=dev
```
## 🧪 Ambiente de Testes (`test`)

Rodar diretamente com **Maven**:
```bash
mvn test 
```

