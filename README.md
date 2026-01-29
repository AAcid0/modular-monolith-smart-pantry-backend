## 🛠️ Stack Tecnológico

| Capa | Tecnología | Justificación |
| :--- | :--- | :--- |
| **Lenguaje** | Java 21 (LTS) | Aprovechamiento de Records, Pattern Matching y Virtual Threads. |
| **Framework** | Spring Boot 3 | Estándar de industria para APIs REST robustas. |
| **Base de Datos** | PostgreSQL 16 + PostGIS | Manejo relacional robusto + capacidades geoespaciales nativas. |
| **ORM** | Spring Data JPA (Hibernate) | Abstracción de acceso a datos. |
| **Infraestructura** | Docker & Docker Compose | Entorno de desarrollo reproducible y aislado. |
| **Seguridad** | Variables de Entorno (.env) | Protección de credenciales según metodología 12-Factor App. |


## 🚀 Instalación y Ejecución

### Prerrequisitos
* Docker & Docker Compose
* Java 21 JDK
* Maven

### Pasos

1.  **Clonar el repositorio:**
    ```bash
    git clone [https://github.com/tu-usuario/modular-monolith-smart-pantry-backend.git](https://github.com/tu-usuario/modular-monolith-smart-pantry-backend.git)
    cd modular-monolith-smart-pantry-backend
    ```

2.  **Levantar Infraestructura (Base de Datos):**
    ```bash
    docker-compose up -d
    ```
    *Esto iniciará PostgreSQL con la extensión PostGIS habilitada.*

3.  **Ejecutar la Aplicación:**
    ```bash
    ./mvnw spring-boot:run
    ```

---


## 🗺️ Roadmap

- [x] Definición de Arquitectura y Modelo de Datos.
- [x] Configuración de Docker y PostGIS.
- [ ] **Fase 1:** API de Gestión de Inventario y Usuarios.
- [ ] **Fase 2:** Lógica de Listas de Compras y Presupuestos.
- [ ] **Fase 3:** Integración de Google ML Kit (OCR) para auditoría.
- [ ] **Fase 4:** Microservicio de Scraping (Python) para precios externos.

---

## 👤 Autor

**Luis Javier** - *Software Engineer*
* [LinkedIn](https://www.linkedin.com/in/luis-ro0/)
* [GitHub](https://github.com/AAcid0)

---
*Este proyecto es parte de un portafolio profesional para demostrar dominio en arquitecturas escalables con Spring Boot.*