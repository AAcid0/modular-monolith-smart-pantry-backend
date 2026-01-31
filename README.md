# 🛒 Smart Pantry & Household Procurement API

> **A robust backend solution designed to digitize, audit, and optimize the domestic supply chain using a Modular Monolith architecture.**

![Java](https://img.shields.io/badge/Java-21-orange?style=flat&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.0.2-green?style=flat&logo=springboot)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue?style=flat&logo=postgresql)
![Architecture](https://img.shields.io/badge/Architecture-Modular_Monolith-purple?style=flat)
![Status](https://img.shields.io/badge/Status-Active_Development-yellow)

## 💡 Project Vision & Context

In today's home economy landscape, resource management is often reactive and fragmented. This inefficiency leads to three critical pain points: **food waste** (inventory inefficiency), **price opacity** (lack of data for decision-making), and **capital leakage** (budget mismanagement).

**Smart Pantry API** was built to solve these issues by transforming the traditional pantry into a **financial audit system**. It is not just a shopping list app; it is a procurement engine that:

1.  **Ensures Data Integrity:** It prioritizes real evidence (processed receipts) over manual input, acting as the "single source of truth" for family consumption.
2.  **Democratizes Market Intelligence:** It leverages geospatial capabilities (PostGIS) to audit and compare price variations across nearby store branches, empowering users with real data.
3.  **Manages Shared Resources:** It digitally models the complexity of "Households," allowing for collaborative inventory management with role-based access control.

This repository hosts the **Core Backend** that powers this logic, serving as the backbone for future mobile (Android/iOS) and web clients.

---

## 🏗️ Architectural Decisions

To handle the domain complexity without the operational overhead of premature distributed microservices, this project adopts a **Modular Monolith** architecture.

### Why this architecture?

* **High Cohesion, Low Coupling:** The system follows a *Package by Feature* structure. Each module (`Inventory`, `Catalog`, `Identity`) is autonomous and exposes an internal public API, encapsulating its own domain logic and persistence.
* **Domain-Driven Design (DDD):** DDD tactics are applied to protect business integrity, isolating the Domain from Infrastructure and UI (Clean Architecture principles).
* **Ready to Scale:** The module boundaries are designed to be easily extracted into independent microservices if the workload or team size requires it in the future, without rewriting business logic.

### Technical Standards & Architecture

This project adheres to strict software engineering standards to ensure scalability, security, and maintainability.

#### **1. Enterprise-Grade Security**
* **Stateless Authentication Architecture:** Implemented via **Spring Security 6** and **JWT**, eliminating server-side sessions for true RESTful compliance.
* **Dual-Token Strategy:** Robust mechanism featuring short-lived **Access Tokens** (15 min) and long-lived, rotatable **Refresh Tokens** (7 days) to balance security with user experience.
* **Cryptographic Protection:** Passwords are salted and hashed using **BCrypt** (work factor 10) prior to persistence.
* **Anti-IDOR Design:** Strict usage of **UUID v4** for all entity identifiers, preventing resource enumeration attacks and ensuring collision-free IDs in distributed systems.

#### **2. Infrastructure & Deployment**
* **Containerization:** Environment-agnostic deployment using **Docker Compose**, ensuring parity between development and production environments (`Write Once, Run Anywhere`).
* **Configuration Management:** Strict separation of config and code via environment variables (`.env`), following **12-Factor App** principles.

#### **3. Data Persistence & Integrity**
* **Schema Validation:** Enforced `ddl-auto=validate` policy to ensure the Java entity model strictly respects the **PostgreSQL** referential integrity, preventing "drift" between code and database.

---

## 🛠️ Tech Stack

| Layer | Technology | Justification |
| :--- | :--- | :--- |
| **Language** | Java 21 (LTS) | Leveraging Records, Pattern Matching, and Virtual Threads. |
| **Framework** | Spring Boot 4.0.2 | Industry standard for building robust REST APIs. |
| **Database** | PostgreSQL 16 + PostGIS | Robust relational management + native geospatial capabilities. |
| **ORM** | Spring Data JPA (Hibernate) | Data access abstraction. |
| **Infrastructure** | Docker & Docker Compose | Reproducible and isolated development environment. |
| **Security** | Environment Variables (.env) | Credential protection following the 12-Factor App methodology. |

---

## 🗺️ Roadmap

- [x] Architecture and Data Model Definition.
- [x] Docker and PostGIS Configuration.
- [ ] **Phase 1:** Inventory and User Management API.
- [ ] **Phase 2:** Shopping List and Budget Logic.
- [ ] **Phase 3:** Google ML Kit (OCR) integration for auditing.
- [ ] **Phase 4:** Scraping Microservice (Python) for external pricing.

---

## 👤 Author

**Luis Javier** - *Software Engineer*
* [LinkedIn](https://www.linkedin.com/in/luis-ro0/)
* [GitHub](https://github.com/AAcid0)

---
*This project is part of a professional portfolio designed to demonstrate mastery in scalable architectures using Spring Boot.*