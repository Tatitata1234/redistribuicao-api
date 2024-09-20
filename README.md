# API de Distribuição de Caixinhas - Java Spring Boot

Esta API desenvolvida em Java com Spring Boot realiza a distribuição de um valor entre caixinhas financeiras. A API roda na porta `18600` e possui dois endpoints principais para distribuir o valor recebido via URL:

1. **Distribuição com caixinhas recebidas pelo corpo da requisição.**
2. **Distribuição com caixinhas predefinidas na API.**

## Tecnologias

- **Java 11+**
- **Spring Boot**
- **Maven**

## Endpoints

### 1. Distribuição com caixinhas via body

- **Método:** `GET`
- **URL:** `/caixinhas?investimento={valor}`
- **Descrição:** Recebe o valor a ser distribuído como parâmetro da URL e as caixinhas são passadas no corpo da requisição.
- **Body:** JSON contendo a lista de caixinhas.

#### Exemplo de Request:

```json
GET /caixinhas?investimento=1000
```
```json
Body:
[
  {
    "nome": "Forno elétrico",
    "total": 700,
    "arrecadado": 404.57,
    "classificacao": "PRECISO_PARA_AGORA",
    "utilidade": "UTIL",
    "quitada": false
  },
  {
    "nome": "The sims",
    "total": 200,
    "arrecadado": 80.84,
    "classificacao": "QUERO",
    "utilidade": "INUTIL",
    "quitada": false
  },
  {
    "nome": "iPad",
    "total": 3500,
    "arrecadado": 64.08,
    "classificacao": "TECNOLOGIA",
    "utilidade": "UTIL",
    "quitada": false
  }
]
```

#### Exemplo de Response:
```json
[
    {
        "nome": "Forno elétrico",
        "total": 700,
        "arrecadado": 404.57,
        "classificacao": "PRECISO_PARA_AGORA",
        "utilidade": "UTIL",
        "investimento": 295.43,
        "quitada": true,
        "mensagem": "Pode comprar!"
    },
    {
        "nome": "The sims",
        "total": 200,
        "arrecadado": 80.84,
        "classificacao": "QUERO",
        "utilidade": "INUTIL",
        "investimento": 83.66,
        "quitada": false,
        "mensagem": ""
    },
    {
        "nome": "iPad",
        "total": 3500,
        "arrecadado": 64.08,
        "classificacao": "TECNOLOGIA",
        "utilidade": "UTIL",
        "investimento": 1.45,
        "quitada": false,
        "mensagem": "Menor que valor programado!"
    }
]
```
### 2. Distribuição com caixinhas predefinidas
- **Método:** `GET`
- **URL:** `/configDefault?investimento={valor}`
- **Descrição:** Recebe o valor a ser distribuído como parâmetro da URL e distribui entre as caixinhas predefinidas configuradas na API.
#### Exemplo de Request:

```json
GET /caixinhas/configDefault?investimento=1000
```

#### Exemplo de Response:
```json
[
    {
        "nome": "Forno elétrico",
        "total": 700,
        "arrecadado": 404.57,
        "classificacao": "PRECISO_PARA_AGORA",
        "utilidade": "UTIL",
        "investimento": 295.43,
        "quitada": true,
        "mensagem": "Pode comprar!"
    },
    {
        "nome": "The sims",
        "total": 200,
        "arrecadado": 80.84,
        "classificacao": "QUERO",
        "utilidade": "INUTIL",
        "investimento": 83.66,
        "quitada": false,
        "mensagem": ""
    },
    {
        "nome": "iPad",
        "total": 3500,
        "arrecadado": 64.08,
        "classificacao": "TECNOLOGIA",
        "utilidade": "UTIL",
        "investimento": 1.45,
        "quitada": false,
        "mensagem": "Menor que valor programado!"
    }
]
```

## Instalação e Execução

### Pré-requisitos

- Java 11+
- Maven

### Passos

1. Clone o repositório:

   `git clone https://github.com/Tatitata1234/redistribuicao-api.git`

2. Acesse o diretório do projeto:

   `cd redistribuicao-api`

3. Compile o projeto com o Maven:

   `mvn clean install`

4. Inicie a aplicação:

   `mvn spring-boot:run`

A API estará disponível em: [http://localhost:18600](http://localhost:18600)

## Como Funciona

- O valor a ser distribuído é enviado via parâmetro de URL (`valor`).
- O usuário pode enviar a lista de caixinhas pelo corpo da requisição ou usar as caixinhas predefinidas configuradas no código da API.
- A resposta será a distribuição do valor entre as caixinhas.
