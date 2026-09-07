# Review

Serviço de avaliações do projeto Saloon Platform.

## Visão Geral

O Review é responsável pelo sistema de avaliações e reviews dos salões e serviços, permitindo que clientes avaliem sua experiência.

## Porta

**5008**

## Funcionalidades

- Criação de avaliações
- Avaliação por estrelas (1-5)
- Comentários textuais
- Listagem de avaliações por salão
- Média de avaliações
- Validação de agendamento prévio

## Endpoints

| Método | Caminho | Descrição |
|--------|---------|-----------|
| POST | `/api/reviews` | Cria nova avaliação |
| GET | `/api/reviews/{id}` | Busca avaliação por ID |
| PUT | `/api/reviews/{id}` | Atualiza avaliação |
| DELETE | `/api/reviews/{id}` | Remove avaliação |
| GET | `/api/reviews/saloon/{saloonId}` | Lista avaliações do salão |
| GET | `/api/reviews/user/{userId}` | Lista avaliações do usuário |

## Tecnologias

- Spring Boot 4.1.1
- Spring Data JPA
- Flyway (migrações)
- MySQL
- OpenFeign
- Eureka Client
- Lombok
- Java 21

## Como Rodar

```bash
mvn clean package
java -jar target/review-0.0.1-SNAPSHOT.jar
```

## Integrações

- **User Service:** Validação de usuários
- **Saloon Service:** Validação de salões
- **Booking Service:** Verificação de agendamento prévio
