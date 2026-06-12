# Blog Project

Proyecto de ejemplo que cubre desde HTML/CSS/JS hasta backend con Spring Boot, Servlets/JSP, JPA, seguridad básica, pruebas y despliegue con Docker.

## Requisitos
- Java 17
- Maven 3.8+
- Docker y docker-compose (para despliegue)
- IntelliJ IDEA o VSCode

## Módulos
- **common**: entidades JPA (Post, Usuario, Categoria, Etiqueta, Comentario, Like, Multimedia)
- **spring-backend**: API REST con Spring Boot
- **servlet-web**: ejemplo MVC con Servlets y JSP
- **frontend-static**: HTML/CSS/JS estático que consume la API

## Ejecutar en desarrollo
```bash
# 1. Compilar todo
mvn -T 1C -B clean package

# 2. Ejecutar backend (H2 en memoria)
mvn -pl spring-backend spring-boot:run -Dspring-boot.run.profiles=dev

# 3. Abrir frontend
# Copiar frontend-static/src/main/resources/static/ a spring-backend/src/main/resources/static/
# o servir con Live Server en VSCode
```

## Docker (con PostgreSQL)
```bash
cp .env.example .env        # edita las variables si quieres
docker-compose up --build
# Backend disponible en http://localhost:8080
```

## Tests
```bash
mvn -pl spring-backend test
```

## API REST
| Método | Endpoint         | Descripción          |
|--------|------------------|----------------------|
| GET    | /api/posts       | Listar todos los posts |
| GET    | /api/posts/{id}  | Obtener post por id  |
| POST   | /api/posts       | Crear post           |
| PUT    | /api/posts/{id}  | Actualizar post      |
| DELETE | /api/posts/{id}  | Eliminar post        |

## CI/CD
Workflow de GitHub Actions en `.github/workflows/ci-cd.yml` compila, ejecuta tests y publica imagen Docker.

Secrets necesarios en GitHub: `DOCKERHUB_REPO`, `DOCKERHUB_USERNAME`, `DOCKERHUB_PASSWORD`.

## Notas
- Seguridad: ejemplo básico. En producción usar BCrypt y JWT/OAuth2.
- Migraciones: Flyway configurado para el perfil `prod`.
- En dev se usa H2 en memoria (no necesita instalación).
