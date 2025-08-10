# Foro Hub API

Proyecto desarrollado como parte del programa **ONE | Tech Foundation - Especialización Back-End**, en el desafío propuesto en la formación de **Java y Spring Framework**.

El objetivo es replicar, a nivel **back-end**, la funcionalidad de un foro para gestionar **tópicos** y sus **respuestas**, creando una **API REST** con **Spring Boot**.

---

## 📌 Funcionalidades

### **Tópicos** (`/topicos`)
- **POST** → Crear un nuevo tópico.
- **GET** → Listar todos los tópicos creados.
- **GET** `/topicos/{id}` → Mostrar un tópico específico.
- **PUT** `/topicos/{id}` → Actualizar un tópico *(solo el autor)*.
- **DELETE** `/topicos/{id}` → Eliminar un tópico *(solo el autor)*.

### **Respuestas por tópico** (`/topicos/{idTopico}/respuestas`)
- **POST** → Registrar una respuesta en un tópico.
- **GET** → Listar respuestas por tópico.
- **GET** `/respuestas/{id}` → Mostrar una respuesta específica.
- **PUT** `/respuestas/{id}` → Actualizar respuesta *(solo el autor)*.
- **DELETE** `/respuestas/{id}` → Eliminar respuesta *(solo el autor)*.
- **PATCH** `/respuestas/{id}/solucion` → Marcar o desmarcar como solución *(solo el autor del tópico)*.

---

## 🛠 Tecnologías utilizadas

- **Java 17**
- **Spring Boot**
- **Maven**
- **MySQL**
- **JPA / Hibernate**
- **Flyway** (migraciones de base de datos)
- **Spring Security** (JWT)
- **Auth0** (manejo de tokens)
- **Bean Validation**
- **Lombok**
- **Spring Boot DevTools**

---

## 📂 Estructura del proyecto

```
src/
 ├── main/
 │   ├── java/com/tuusuario/forohub
 │   │    ├── controller           # Controladores REST
 │   │    ├── domain               # Capa de dominio (servicios, lógica de negocio)
 │   │    │     └── model           # Entidades 
 │   │    ├── infra                 # Infraestructura
 │   │    │     ├── exceptions      # Manejo de errores y excepciones personalizadas
 │   │    │     └── security        # Seguridad, JWT, filtros, configuración
 │   └── resources/
 │        ├── db/migration          # Migraciones de base de datos con Flyway
 │        └── application.properties

```
---

## ⚙️ Configuración del proyecto

La aplicación se conecta a una base de datos **MySQL**.  
Para ejecutarla, asegúrate de tener MySQL corriendo y de haber creado una base de datos con el nombre que definas en tu variable de entorno `DB_NAME`.

A continuación, la configuración segura del archivo `application.properties` utilizando **variables de entorno**:

```
spring.application.name=foro-api

spring.datasource.url=jdbc:mysql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.error.include-stacktrace=never

api.security.token.secret=${JWT_SECRET}
```

---

Debes configurar las siguientes variables de entorno en tu sistema:

| Variable      | Descripción                               | Ejemplo              |
| ------------- | ----------------------------------------- | -------------------- |
| `DB_HOST`     | Host y puerto de tu base de datos         | `localhost:3306`     |
| `DB_NAME`     | Nombre de la base de datos                | `foro_hub`           |
| `DB_USER`     | Usuario de la base de datos               | `root`               |
| `DB_PASSWORD` | Contraseña del usuario                    | `123456`             |
| `JWT_SECRET`  | Clave secreta para la firma de tokens JWT | `clave_super_segura` |

---

## 📜 Licencia
Este proyecto fue desarrollado con fines educativos como parte del programa Oracle Next Education.
Puedes usarlo, modificarlo y adaptarlo libremente.