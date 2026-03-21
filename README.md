# DigitalBankGateway & DigitalBankData

🇧🇷 [Leia em Português](README.pt-BR.md)

---

## Overview

This project consists of two microservices: **DigitalBankGateway** and **DigitalBankData**.

The goal is to build a RESTful digital banking system using:

* Java 21
* Spring Boot
* Apache Kafka
* OpenFeign

---

## ⚠️ Encoding (UTF-8)

This project uses **UTF-8** as the default encoding.

### Possible Issue

In some environments (especially Windows), you may encounter the following error during build:

```
MalformedInputException: Input length = 1
```

### Cause

Encoding mismatch when processing `.properties` files (especially with special/accented characters).

### Solutions

* The project is already configured to use UTF-8 in `pom.xml`
* Ensure your IDE is also using UTF-8:

   * IntelliJ: `File Encoding = UTF-8`
   * VS Code: `Change File Encoding → UTF-8`

### Note

To avoid cross-environment issues, we chose **not to use special characters (accents)** in `.properties` files.

---

## Microservices

### 1. DigitalBankGateway

* Entry point for business logic
* Sends messages via Kafka for create/update/delete operations
* Uses Feign to query data (`find`, `findAll`) from DigitalBankData

### 2. DigitalBankData

* Responsible for data persistence
* Consumes Kafka messages for data operations
* Exposes REST endpoints for queries (`find`, `findAll`)

---

## Project Structure

```plaintext
project-name/
├── src/
│   ├── main/java/br/com/project/
│   │   ├── core
│   │   │   ├── dataprovider
│   │   │   ├── domain
│   │   │   └── usecase
│   │   ├── dataprovider
│   │   │   ├── client
│   │   │   ├── repository
│   │   ├── entrypoint
│   │   │   ├── controller
├── test/
└── resources/
```

---

## Features

### DigitalBankGateway

* Customer registration
* Transfers
* Deposits
* Transaction logging

### DigitalBankData

* Kafka-based data operations (create/update/delete)
* Feign-based queries (`find`, `findAll`)

---

## Setup

### 1. Clone repository

```
git clone <repo-url>
```

### 2. Build project

```
mvn clean install
```

### 3. Run services

```
mvn spring-boot:run
```

Run both microservices.

---

## Contributing

Feel free to contribute!

1. Fork the repository
2. Create a branch (`git checkout -b feature/my-feature`)
3. Commit (`git commit -m 'Add feature'`)
4. Push (`git push origin feature/my-feature`)
5. Open a Pull Request

---

## Contact

For questions or suggestions, feel free to reach out 🙂
