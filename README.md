# ChallengeApiRestForHub

# 🗣️ ForoHub API - Alura Challenge

API REST para gestión de un foro de discusión, desarrollada con **Spring Boot 3** y **Java 17**.

---

## 🚀 Tecnologías

- Java 17
- Spring Boot 3.2
- Spring Security + JWT (Auth0)
- Spring Data JPA + Hibernate
- Flyway (migraciones)
- MySQL 8
- Lombok
- SpringDoc OpenAPI (Swagger)

---

## ⚙️ Configuración

### 1. Clonar el proyecto
```bash
git clone https://github.com/tu-usuario/forohub.git
```

### 2. Crear base de datos MySQL
```sql
CREATE DATABASE forohub_db;
```

### 3. Configurar application.properties
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/forohub_db
spring.datasource.username=root
spring.datasource.password=tu_password
api.security.secret=tu_secret_jwt
```

### 4. Ejecutar el proyecto
```bash
./mvnw spring-boot:run
```

---

## 📌 Endpoints

### 🔐 Autenticación
| Método | URI | Descripción |
|--------|-----|-------------|
| POST | `/login` | Obtener token JWT |

### 💬 Tópicos
| Método | URI | Descripción |
|--------|-----|-------------|
| POST | `/topicos` | Crear tópico |
| GET | `/topicos` | Listar tópicos |
| GET | `/topicos?curso=Spring&año=2024` | Filtrar por curso y año |
| GET | `/topicos/{id}` | Detalle de tópico |
| PUT | `/topicos/{id}` | Actualizar tópico |
| DELETE | `/topicos/{id}` | Eliminar tópico |

### 👤 Usuarios
| Método | URI | Descripción |
|--------|-----|-------------|
| POST | `/usuarios` | Registrar usuario |
| GET | `/usuarios` | Listar usuarios |
| DELETE | `/usuarios/{id}` | Eliminar usuario |

### 💡 Respuestas
| Método | URI | Descripción |
|--------|-----|-------------|
| POST | `/respuestas` | Crear respuesta |
| GET | `/respuestas/topico/{id}` | Listar por tópico |
| DELETE | `/respuestas/{id}` | Eliminar respuesta |

---

## 📚 Documentación Swagger

Con el proyecto corriendo, accede a:
```
http://localhost:8080/swagger-ui.html
```

---

## 👨‍💻 Autor
Tu Nombre — [LinkedIn](https://linkedin.com) — [GitHub](https://github.com)
