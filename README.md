# Courier Tracking Application

<img src="/documents/icon.png?raw=true" alt="Icon" width="60"/>

This application is a modular, scalable, and maintainable Spring Boot application demonstrating a HexaLayered
Architecture for real-time courier tracking. The system processes streaming geolocation data
from couriers and provides the following key features:

### **Key Features**

- **Geolocation Streaming**: Accept real-time courier location updates (timestamp, courier ID, latitude, longitude).
- **Entry Logging**: Log an entry event when a courier enters within a 100-meter radius of Seller stores. Re-entries
  within 1 minute are ignored to prevent duplicate logging.
- **Distance Calculation**: Compute the cumulative travel distance for each courier based on successive location
  updates.
- **HexaLayered Architecture**: Maintain a clear separation of concerns across presentation, application, domain,
  infrastructure, and integration layers.

---

## Tech Stack

**Language**

* Java 17

**Framework**

* Spring Boot 3.4.x
    * Spring Boot Web
    * Spring Data MongoDB
    * Spring Boot Actuator
    * Spring Boot Test (Junit)
    * Spring Boot AMQP

**Build Tool**

* Maven

**NoSQL Database**

* MongoDB

**3rd Party Dependencies**

* Lombok
* MapStruct
* Easy Random

**Message Broker**

* RabbitMQ

**Software Development Process**

* TDD

**Version Control**

* Git
* GitHub

**APIs Interaction Platform**

* Postman

---

# Getting Started

## Building The Project With Tests

```bash
./mvnw clean install
```

## Building The Project Without Tests

```
./mvnw clean install -DskipTests
```

## Running Dependencies as Containers

You can execute the following command to start the MongoDB, RabbitMQ and Application containers:

```
docker compose up -d --build
```

If you want to recreate MongoDB, RabbitMQ and Application containers, you can run the following command:

```
docker compose up --force-recreate -d --build
```

If you want to stop MongoDB, RabbitMQ and Application containers, you can run the following command:

```
docker compose down -v
```

---

## Postman

### [Workspace](https://www.postman.com/agitrubard/workspace/courier-tracking-application) & [API Documentation](https://documenter.getpostman.com/view/23090035/2sAYkAQ2fr)

---

# Project Infrastructure

## 🏛️ HexaLayered Architecture

![](/documents/architecture/hexalayered-architecture.png?raw=true)

> **Reference: [HexaLayered Architecture](https://github.com/agitrubard/hexalayered-architecture)**
