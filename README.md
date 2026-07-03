# Serviço de Transferências - Banco RM

## Sobre o Projeto
Este projeto é o microsserviço principal (Produtor) do laboratório de arquitetura do Banco RM. É responsável por orquestrar transferências bancárias aplicando regras de negócio de forma isolada, seguindo os rigorosos princípios da Arquitetura Hexagonal. Após o processamento da regra, atua como Produtor de eventos publicando mensagens de sucesso no Apache Kafka.

## Tecnologias Utilizadas
* **Java 21**
* **Spring Boot 3**
* **Apache Kafka** (Spring for Apache Kafka)
* **Maven**

## Arquitetura Hexagonal (Ports and Adapters)
A aplicação foi meticulosamente estruturada para manter o Domínio de negócio protegido e isolado de tecnologias externas:
* **Domínio (O Centro):** `Conta` e `RegraTransferencia` (Desenvolvidos em Java 21 puro, sem dependências de frameworks. Nenhuma regra de negócio conhece a base de dados ou a web).
* **Portas (Os Contratos):** `TransferirDinheiroUseCase` (Entrada), `ContaRepository` e `NotificarTransferenciaPort` (Saída).
* **Adaptadores de Entrada:** `TransferenciaController` (Controlador REST a escutar pedidos HTTP POST).
* **Adaptadores de Saída:** `ContaDatabaseAdapter` (Simulação de base de dados em memória utilizando Map) e `NotificacaoKafkaAdapter` (Produtor que traduz e envia o evento para o Kafka).
* **Orquestração:** `TransferirDinheiroService` (O "Maestro" que coordena o fluxo entre as Portas e o Domínio).

## Como Executar
1. **Infraestrutura:** Certifique-se de que o Apache Kafka está a correr no seu ambiente Docker (porta `9092`).
2. **Compilação:** Na raiz do projeto, execute o comando `mvn clean install` para compilar o código.
3. **Execução:** Inicie a aplicação Spring Boot através da sua IDE ou executando `mvn spring-boot:run`. O serviço irá iniciar na **porta 8080**.

## Como Testar
Com o serviço a correr, envie um pedido HTTP POST simulando o envio através de uma aplicação de celular:

```bash
curl -X POST http://localhost:8080/transferencias -H "Content-Type: application/json" -d "{\"contaOrigem\": \"1\", \"contaDestino\": \"2\", \"valor\": 50.00}"
```
Deverá receber a resposta de sucesso e, na consola da aplicação, visualizar o log do Adaptador de Saída a confirmar o envio para o Kafka: 🚀 PRODUTOR: A mensagem de transferência foi enviada para o Kafka!