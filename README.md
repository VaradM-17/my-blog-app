# 📖 Blog Application

A full-featured **Blog Application** built with **Spring Boot, Spring Security, and JWT**.  
It supports **user authentication, role-based access (ADMIN & USER), posts, comments, and pagination**.

---

## 📂 Project Structure

```
blog-app/
│── src/main/java/com/security/blog/
│   ├── config/          # Security configurations
│   ├── controller/      # REST controllers
│   ├── dto/             # Data Transfer Objects (LoginDto, RegisterDto, etc.)
│   ├── entity/          # Entities (Post, Comment, User)
│   ├── exception/       # Custom exceptions & handlers
│   ├── repository/      # Spring Data JPA repositories
│   ├── security/        # JWT classes (Filter, TokenProvider, EntryPoint)
│   ├── service/         # Business logic layer
│   └── BlogAppApplication # Main Spring Boot application
│
└── src/main/resources/
    ├── application.properties # Database & JWT configurations
```

---

## ⚙️ Installation & Setup

1. **Clone the repository**
   ```bash
   git clone https://github.com/VaradM-17/my-blog-app.git
   cd my-blog-app/blog-app
   ```

2. **Configure Database**  
   Open `src/main/resources/application.properties` and update it with your DB credentials:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/blog_app
   spring.datasource.username=root
   spring.datasource.password=yourpassword
   spring.jpa.hibernate.ddl-auto=update

   # JWT properties
   app.jwt-secret=your_jwt_secret_key
   app.jwt-expiration-milliseconds=604800000
   ```

3. **Build & Run the application**
   ```bash
   mvn spring-boot:run
   ```

---

## 🚀 Features

- 🔐 **Authentication & Authorization** using **Spring Security + JWT**
- 👥 **Role-based access** (`ADMIN`, `USER`)
- 📝 **Posts & Comments** (One-to-Many relationship)
- 📑 **Pagination & Sorting** for posts
- 📦 **DTOs** for clean API requests/responses
- ⚠️ **Exception handling**
- 🗄️ **MySQL Database** with JPA & Hibernate

---

## 🔑 API Endpoints

### 🔐 Authentication
| Endpoint | Method | Access | Description |
|----------|--------|--------|-------------|
| `/api/auth/register` | POST | Public | Register a new user |
| `/api/auth/login` | POST | Public | Login and receive JWT token |

### 📝 Posts
| Endpoint | Method | Access | Description |
|----------|--------|--------|-------------|
| `/api/posts` | POST | ADMIN | Create a new post |
| `/api/posts` | GET | USER, ADMIN | Get all posts (with pagination & sorting) |
| `/api/posts/{id}` | GET | USER, ADMIN | Get a post by ID |
| `/api/posts/{id}` | PUT | ADMIN | Update a post |
| `/api/posts/{id}` | DELETE | ADMIN | Delete a post |

### 💬 Comments
| Endpoint | Method | Access | Description |
|----------|--------|--------|-------------|
| `/api/posts/{postId}/comments` | POST | USER, ADMIN | Add a comment to a post |
| `/api/posts/{postId}/comments` | GET | USER, ADMIN | Get all comments for a post |
| `/api/comments/{id}` | DELETE | USER, ADMIN | Delete a comment |

---

## 🔒 JWT Security Flow

1. User registers or logs in → receives a **JWT token**  
2. Client sends JWT in the `Authorization` header:  
   ```http
   Authorization: Bearer <your_token>
   ```
3. Every request is checked by `JwtAuthenticationFilter`  
4. If the token is valid → request continues  
5. If invalid → `JwtAuthenticationEntryPoint` returns **Unauthorized (401)**  

---
