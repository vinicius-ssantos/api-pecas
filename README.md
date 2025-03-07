# Projeto API com Kong API Gateway

Este projeto é uma API desenvolvida com **Spring Boot** e **PostgreSQL**, utilizando o **Kong API Gateway** para gerenciar o tráfego e segurança dos endpoints.

---

## **1. Pré-requisitos**

Antes de começar, certifique-se de ter os seguintes requisitos instalados:

- **Docker** e **Docker Compose**
- **Postman** (opcional, mas recomendado)
- **Java 17** (OpenJDK recomendado)
- **Maven**

---

## **2. Configuração e Instalação**

### **2.1. Clonar o repositório**
```sh
  git clone https://github.com/seu-repositorio/api-trabalho.git
  cd api-trabalho
```

### **2.2. Compilar o projeto**
Antes de rodar o Docker, compile o projeto para gerar o `.jar` na pasta `target/`:
```sh
  mvn clean package -DskipTests
```

---

## **3. Executando com Docker**

Para iniciar todos os serviços (**Banco de Dados, API e Kong**), execute:
```sh
  docker-compose up -d --build
```

Após subir os serviços, confirme se os contêineres estão rodando:
```sh
  docker ps
```

Se precisar reiniciar um serviço específico:
```sh
  docker-compose restart <nome-do-servico>
```

---

## **4. Configuração do Kong API Gateway**

Após subir os serviços, execute o seguinte script para configurar as rotas no Kong:

### **4.1. Criar o script `setup-kong.sh`**
```sh
#!/bin/bash

# Aguarda o Kong inicializar
echo "Aguardando o Kong iniciar..."
sleep 10

# Criando um serviço no Kong apontando para a API
curl -i -X POST --url http://localhost:8001/services/ \
  --data 'name=api-trabalho' \
  --data 'url=http://springboot_api:8088' || echo "Serviço já existe. Continuando..."

# Criando uma rota para a API
curl -i -X POST --url http://localhost:8001/services/api-trabalho/routes \
  --data 'paths[]=/api/v1/peca' \
  --data 'name=peca-route' || echo "Rota já existe. Continuando..."

# Exibe as rotas configuradas
curl -i -X GET --url http://localhost:8001/routes
```

### **4.2. Executar o script**
```sh
  chmod +x setup-kong.sh
  ./setup-kong.sh
```

Agora a API pode ser acessada via Kong em:
```
http://localhost:8000/api/v1/peca
```

---

## **5. Testando a API**

### **5.1. Testando no Postman**

#### **Criar uma Peça**
- **Método:** `POST`
- **URL:** `http://localhost:8000/api/v1/peca`
- **Body (JSON):**
```json
{
  "codigo": "123",
  "nome": "Motor X",
  "descricao": "Peça de motor de alta performance"
}
```

#### **Obter todas as Peças**
- **Método:** `GET`
- **URL:** `http://localhost:8000/api/v1/peca`

#### **Obter uma Peça por Código**
- **Método:** `GET`
- **URL:** `http://localhost:8000/api/v1/peca/123`

---

## **6. Parando os Contêineres**

Caso precise parar os serviços, execute:
```sh
  docker-compose down
```

---

## **7. Estrutura do Projeto**
```
/api-trabalho
│── src/main/java/com/viniciussantos/api/trabalho/
│   ├── controllers/
│   ├── models/
│   ├── repositories/
│   ├── services/
│── src/main/resources/
│   ├── application.yml
│── Dockerfile
│── docker-compose.yml
│── setup-kong.sh
│── README.md
```

---

## **8. Tecnologias Utilizadas**

- **Spring Boot**
- **PostgreSQL**
- **Docker & Docker Compose**
- **Kong API Gateway**
- **Maven**

---

## **9. Debug e Problemas Comuns**

### **9.1. Banco de Dados não conectando**
```sh
  psql -h localhost -p 5432 -U username -d pecas_db
```
Se houver erro de conexão, verifique se o contêiner está rodando:
```sh
  docker ps
```
E reinicie:
```sh
  docker-compose restart postgres
```

### **9.2. Erro `target/*.jar not found`**
Se ao executar `docker-compose up -d --build` houver erro **"target/*.jar not found"**, rode:
```sh
  mvn clean package -DskipTests
```
E depois:
```sh 
  docker-compose up -d --build
```

### **9.3. Kong não inicia corretamente**
Se o Kong apresentar erro de banco de dados, execute:
```sh
  docker-compose restart kong
```
E reexecute as migrações manualmente:
```sh
  docker-compose run --rm kong-migrations kong migrations bootstrap
```

---

## **10. Contribuição**

Caso queira contribuir:
1. Faça um **fork** do repositório.
2. Crie uma **branch** (`feature/minha-feature`).
3. Envie um **pull request**.

---

## **11. Autor**

Projeto desenvolvido por **Vinícius Santos**. 🚀

