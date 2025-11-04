# 🛠️ BogotáCup API – Backend para Gestión de Torneos de Fútbol Amateur

El proyecto **BogotáCup API** es el backend desarrollado para el sistema de gestión de torneos de fútbol amateur en Bogotá. Funciona como servicio REST que permite administrar torneos, equipos, jugadores, partidos, etc., y se conecta al frontend del sistema principal.

---

## 📋 Descripción del Proyecto

Este API sirve como capa de negocio y acceso a datos del sistema de gestión de torneos. Permite a aplicaciones clientes consultar, crear, modificar y eliminar entidades como torneos, equipos, jugadores, partidos, estadísticas, entre otros. Su objetivo es centralizar la lógica del negocio y facilitar la integración de múltiples interfaces de usuario.

---

## 🎯 Objetivos

### Objetivo General

Desarrollar un **servicio RESTful** que soporte las operaciones de gestión del sistema de torneos amateur, con endpoints para manejar las principales entidades del dominio.

### Objetivos Específicos

* Modelar los recursos del dominio (torneos, equipos, jugadores, partidos) como entidades RESTful.
* Implementar persistencia mediante una base de datos relacional.
* Proteger el API mediante autenticación/autorización (por ejemplo roles administrador, entrenador).
* Proveer documentación de los endpoints (Swagger/OpenAPI u otro formato).
* Integrar control de errores, validación de datos y respuestas consistentes JSON.

---

## 🗄️ Funcionalidades Principales

* Autenticación y gestión de usuarios con roles (admin, entrenador, jugador).
* CRUD de torneos: creación, actualización, consulta, cierre.
* CRUD de equipos y jugadores: asignación de plantillas a equipos, registro de jugadores.
* Programación y gestión de partidos: asignar fecha, hora, canchas, árbitros.
* Registro de resultados y generación de tablas de posiciones.
* Endpoints de consulta para estadísticas, listados y filtros.

---

## 🧩 Entidades Principales (recapituladas)

1. Usuario
2. Torneo
3. Equipo
4. Jugador
5. Partido
6. Árbitro
7. Cancha
8. Resultado
9. TablaPosiciones
10. Rol / Permiso

---

## 💡 Supuestos Técnicos

* La base de datos es relacional, debidamente normalizada hasta 3FN.
* El entorno de ejecución está alojado en servidor Linux o plataforma en la nube.
* El API utiliza arquitectura cliente-servidor, sin interfaz HTML propia (solo JSON).
* No se contempla en esta versión la integración con terceros (pagos, redes sociales, etc.).

---

## 🔧 Tecnologías Utilizadas (ejemplo)

* Backend: Spring Boot (o equivalente)
* Base de Datos: PostgreSQL o MariaDB
* Autenticación: JWT (o mecanismo similar)
* Documentación de API: Swagger UI / OpenAPI
* Control de versiones: Git + GitHub
* Entorno de despliegue: servidor Linux o contenedor Docker (opcional)

---

## 🚀 Instalación y Ejecución

1. Clonar el repositorio:

   ```bash
   git clone https://github.com/nikotpab/BogotaCup_API.git
   cd BogotaCup_API
   ```
2. Configurar la conexión a la base de datos en el archivo de configuración (por ejemplo `application.properties` o `application.yml`).
3. Crear o migrar la base de datos mediante el script SQL o herramienta de migración (ej. Flyway, Liquibase).
4. Compilar e iniciar el servicio:

   ```bash
   ./mvnw clean install
   ./mvnw spring-boot:run
   ```

   o equivalente si usas otra tecnología.
5. Acceder a la documentación del API (por ejemplo `http://localhost:8080/swagger-ui.html`).
6. Usar herramientas como Postman o cURL para probar los endpoints.

---

## 🧪 Endpoints de Ejemplo

* `POST /api/auth/login` — Autenticar usuario.
* `GET /api/torneos` — Listar todos los torneos.
* `POST /api/torneos` — Crear un nuevo torneo.
* `PUT /api/equipos/{id}` — Actualizar equipo.
* `GET /api/partidos/{id}/resultado` — Consultar resultado del partido.
* `GET /api/tablas/{torneoId}` — Obtener tabla de posiciones de un torneo.

*(Estos endpoints son ejemplos; ajusta según el diseño real del API.)*

---

## 👥 Autores y Contribución

El proyecto ha sido desarrollado como parte de un curso de Ingeniería de Sistemas, y el repositorio está abierto para colaboración académica. Si deseas contribuir:

1. Haz un fork del repositorio.
2. Crea una rama feature/tu-modificación.
3. Envía un pull request con descripción clara de los cambios.

---

## 📜 Licencia

Este proyecto se distribuye con fines **académicos** y **no comerciales**, bajo una licencia de tipo libre para consulta y aprendizaje.
