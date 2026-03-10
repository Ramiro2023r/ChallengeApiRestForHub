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
                                                   Imagenes de el funcionamiento 


<img width="669" height="275" alt="image" src="https://github.com/user-attachments/assets/205a9d83-5ce8-450d-ba23-549fae81a524" />
<img width="432" height="111" alt="image" src="https://github.com/user-attachments/assets/291042e1-8c47-4501-9b28-e9ea6ba6c0ba" />
<img width="1281" height="173" alt="image" src="https://github.com/user-attachments/assets/00eb1463-baf8-4068-90a4-d84ee818a7fd" />

enpoints
<img width="1339" height="238" alt="image" src="https://github.com/user-attachments/assets/6374ec6a-101c-44fc-bf98-822803c41e9a" />
<img width="1323" height="334" alt="image" src="https://github.com/user-attachments/assets/2e30b3ce-283c-4792-bf2a-6c17e181869d" />
<img width="1341" height="348" alt="image" src="https://github.com/user-attachments/assets/c633ea7a-2791-44a7-96a3-496c2c370f30" />

<img width="524" height="40" alt="image" src="https://github.com/user-attachments/assets/11104253-6e50-432f-89a9-d1f8f77803d4" />
<img width="946" height="482" alt="image" src="https://github.com/user-attachments/assets/84735048-96a4-41c7-9e6d-8ef14ed89d5e" />
<img width="945" height="31" alt="image" src="https://github.com/user-attachments/assets/9e44bd7c-ad28-4fc8-a7f8-edae88bb4ffd" />
<img width="953" height="449" alt="image" src="https://github.com/user-attachments/assets/ff6042a2-25d5-4918-8921-b19bf8d5b9d9" />
<img width="934" height="146" alt="image" src="https://github.com/user-attachments/assets/5c6459e1-2da2-4bd6-8a76-e55dd460b7eb" />

Base de datos MYSQL 

<img width="401" height="172" alt="image" src="https://github.com/user-attachments/assets/cabf4702-4407-4006-a16b-dad4776380ce" />
<img width="1911" height="867" alt="image" src="https://github.com/user-attachments/assets/b09dee98-2ad3-4cb2-a255-50b157a61d7c" />

IDE


<img width="1860" height="968" alt="image" src="https://github.com/user-attachments/assets/43e97c04-7c42-4dff-89a7-7df17adc94ed" />

---

## 👨‍💻 Autor
L9TDeveloper — [LinkedIn](www.linkedin.com/in/ramiro-huaman-acosta) — [GitHub](https://github.com/Ramiro2023r)
