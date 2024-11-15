# DigitalBankGateway & DigitalBankData

## Descrição

Este projeto é composto por dois microserviços: **DigitalBankGateway** e **DigitalBankData**. O objetivo é criar um sistema de banco digital RESTful, utilizando Spring Boot, Kafka e Feign para comunicação entre os microserviços.

### **Microserviços**

1. **DigitalBankGateway**
   - Responsável pela lógica de entrada.
   - Envia mensagens através do Kafka para o microserviço de banco de dados para operações de inserção, atualização e deleção.
   - Utiliza Feign para realizar operações de busca geral (`find`, `findAll`) no microserviço de banco de dados.

2. **DigitalBankData**
   - Responsável pelo armazenamento e manipulação dos dados.
   - Recebe mensagens do Kafka e realiza operações de inserção, atualização e deleção no banco de dados.
   - Exponibiliza endpoints para operações de busca geral (`find`, `findAll`) acessíveis pelo microserviço de lógica de entrada via Feign.

## Estrutura Base
```plaintext
nome_do_projeto/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── br.com.nome_do_projeto/
│   │   │   │   ├── core
│   │   │   │   │   ├── dataprovider
│   │   │   │   │   ├── domain
│   │   │   │   │   └── usecase
│   │   │   │   ├── dataprovider
│   │   │   │   │   ├── client
│   │   │   │   │   │   ├── mapper
│   │   │   │   │   │   └── response
│   │   │   │   │   ├── repository
│   │   │   │   │   │   ├── entity
│   │   │   │   │   │   └── mapper
│   │   │   │   ├── entrypoint
│   │   │   │   │   ├── controller
│   │   │   │   │   │   ├── mapper
│   │   │   │   │   │   ├── request
│   │   │   │   │   │   └── response
├── test/
│   ├── java/
│   │   ├── br.com.nome_do_projeto/
│   │   │   ├── core
│   │   │   ├── dataprovider
│   │   │   ├── entrypoint
└── resources
    ├── application.properties
    └── outros arquivos de configuração (e.g., logback.xml)
```

## Funcionalidades

### **DigitalBankGateway**
- Cadastro de Cliente
- Transferência
- Depósito
- Envio de logs de transações para o sistema

### **DigitalBankData**
- **Mensageria (Kafka) para operações de inserção, atualização e deleção**
- **Feign para operações de busca como `find` e `findAll`**

## Configuração dos Microserviços

### **DigitalBankGateway**
1. **Configuração do Kafka:**
   - Adicione as dependências do Kafka no `pom.xml`.
   - Configure o Kafka no `application.properties`.
   - Crie a classe de configuração do Kafka.

2. **Criação das Entidades e DTOs:**
   - Defina as classes `ClienteDTO` e `TransacaoDTO`.

3. **Configuração dos Controladores REST:**
   - Crie controladores para gerenciar as requisições de cadastro de clientes e transações.

4. **Serviços:**
   - Configure os serviços para processar as requisições e enviar mensagens para o Kafka.

### **DigitalBankData**
1. **Configuração do Kafka e Feign:**
   - Adicione as dependências do Kafka e Feign no `pom.xml`.
   - Configure o Kafka no `application.properties`.

2. **Criação das Entidades e Repositórios:**
   - Defina as entidades `Cliente` e `Transacao`.
   - Crie os repositórios para gerenciar as operações no banco de dados.

3. **Serviços:**
   - Configure os serviços para salvar e buscar dados.

4. **Consumers do Kafka:**
   - Crie consumidores do Kafka para processar mensagens recebidas.

5. **Clientes Feign:**
   - Configure os clientes Feign para comunicação entre os microserviços.

## Como Executar

1. **Passo 1:** Clone o repositório.
2. **Passo 2:** Navegue até o diretório dos microserviços e execute `mvn clean install` para instalar as dependências.
3. **Passo 3:** Inicie os microserviços executando `mvn spring-boot:run` em ambos os diretórios.

Pronto! Agora você tem um sistema de banco digital RESTful com dois microserviços integrados utilizando Spring Boot, Kafka e Feign. 🚀

## Contribuição

Sinta-se à vontade para contribuir com melhorias e novas funcionalidades. Para contribuir:
1. Fork o repositório.
2. Crie um branch para a sua feature (`git checkout -b minha-feature`).
3. Commit suas alterações (`git commit -m 'Adiciona minha feature'`).
4. Push para o branch (`git push origin minha-feature`).
5. Crie um Pull Request.

---

*Para mais detalhes ou dúvidas, entre em contato.* 😉
