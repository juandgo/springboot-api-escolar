# Documentación de la Prueba Técnica - Sistema de Registro Escolar

## Descripción General

Este proyecto fue desarrollado como parte de una prueba técnica para un cargo de Desarrollador Mid. El objetivo principal fue construir un sistema CRUD (Crear, Leer, Actualizar, Eliminar) completo para la gestión de personas dentro de una institución educativa, abarcando estudiantes, profesores y personal administrativo. La solución se implementó utilizando **Java (Spring Boot)** para el backend y **Angular con Angular Material** para el frontend, siguiendo principios de diseño responsivo.

## Tecnologías Utilizadas

### Backend (Java Spring Boot)
* **Java 17**
* **Spring Boot 3**
    * `Spring Web`
    * `Spring Data JPA`
    * `Spring Validation`
* **ModelMapper**: Para una conversión eficiente entre DTOs y Entidades.
* **Base de datos**: MySQL (local), con soporte para PostgreSQL vía Docker.
* **Gestor de dependencias**: Maven

### Frontend (Angular)
* **Angular 17**
* **Angular Material**: Para componentes UI y diseño.
* **TypeScript**
* **SCSS**
* **Diseño Responsivo**

## Estructura del Backend

### Entidades
* `Persona` (clase abstracta)
* `Profesor` (hereda de `Persona`)
* `Estudiante` (hereda de `Persona`)
* *(Bonus no implementado en esta fase: Administrativo, Curso, Inscripción)*

### Paquetes Principales
* `controller`: Controladores RESTful para manejar las peticiones de Profesor y Estudiante.
* `service`: Capa de lógica de negocio con interfaces y sus implementaciones.
* `repository`: Interfaces basadas en Spring Data JPA para el acceso a datos.
* `dto`: Clases Data Transfer Object (DTO) con validaciones para la transferencia de datos.
* `mapper`: Clases encargadas de la conversión entre DTOs y entidades utilizando ModelMapper.
* `config`: Configuraciones generales de la aplicación, incluyendo la configuración de ModelMapper.
* `exception`: Manejo global de errores mediante `@ControllerAdvice`, retornando respuestas JSON estandarizadas.

### Funcionalidades Principales
* **CRUD completo** para las entidades `Profesor` y `Estudiante`.
* **Validaciones detalladas en DTOs:**
    * Formato de email válido.
    * Teléfono numérico.
    * Fechas de nacimiento no futuras.
* **Excepciones personalizadas** con respuestas JSON informativas.

### Bonus Implementado
* Uso de **ModelMapper** para mapeo de objetos.
* Implementación de una **Arquitectura en capas** (Controller, Service, Repository).

## Estructura del Frontend

### Módulos Creados
* `NavbarComponent` (Componente de navegación principal)
* `RegistroProfesorComponent`
* `ListarProfesoresComponent`
* `RegistroEstudianteComponent`
* `ListarEstudiantesComponent`

### Características Destacadas
* Formularios reutilizables para la creación y edición de profesores y estudiantes.
* Listados presentados en tablas interactivas con `mat-table` de Angular Material.
* Botones de acción para edición y eliminación de registros.
* Validaciones reactivas en los formularios, proporcionando feedback inmediato al usuario.
* Navegación intuitiva mediante `routerLink`.
* Estilos consistentes y componentes de UI robustos provistos por Angular Material.

### Servicios
* `ProfesorService` y `EstudianteService`: Encargados de realizar las llamadas HTTP al backend para la gestión de datos.

## Instrucciones para Ejecutar el Proyecto

Asegúrate de tener Java 17, Maven, Node.js y npm instalados en tu sistema.

### 1. Backend (Java Spring Boot)

1.  Navega al directorio `backend`:
    ```bash
    cd backend
    ```
2.  Ejecuta la aplicación Spring Boot:
    ```bash
    mvn spring-boot:run
    ```
    El backend estará disponible en: `http://localhost:8080`

### 2. Frontend (Angular)

1.  Navega al directorio `frontend`:
    ```bash
    cd frontend
    ```
2.  Instala las dependencias del proyecto:
    ```bash
    npm install
    ```
3.  Inicia la aplicación Angular:
    ```bash
    ng serve
    ```
    El frontend estará disponible en: `http://localhost:4200`

### 3. Docker (Opcional - con PostgreSQL)

Para levantar el servicio del backend usando PostgreSQL en un contenedor Docker:

1.  Asegúrate de tener Docker instalado.
2.  Navega al directorio raíz del proyecto donde se encuentra el `docker-compose.yml`.
3.  Ejecuta el siguiente comando:
    ```bash
    docker-compose up -d
    ```
    Esto levantará los servicios definidos en el `docker-compose.yml` (normalmente el backend y una base de datos PostgreSQL). Asegúrate de configurar el backend para que se conecte a esta instancia de PostgreSQL si no está ya configurado por defecto.

## Endpoints REST Disponibles

### Profesores
* `GET /api/profesores` : Obtener todos los profesores.
* `GET /api/profesores/{id}` : Obtener un profesor por ID.
* `POST /api/profesores` : Crear un nuevo profesor.
* `PUT /api/profesores/{id}` : Actualizar un profesor existente.
* `DELETE /api/profesores/{id}` : Eliminar un profesor.

### Estudiantes
* `GET /api/estudiantes` : Obtener todos los estudiantes.
* `GET /api/estudiantes/{id}` : Obtener un estudiante por ID.
* `POST /api/estudiantes` : Crear un nuevo estudiante.
* `PUT /api/estudiantes/{id}` : Actualizar un estudiante existente.
* `DELETE /api/estudiantes/{id}` : Eliminar un estudiante.

## Validaciones Realizadas

### Backend (DTOs)
* `@NotBlank`: Asegura que los campos no estén vacíos.
* `@Email`: Valida el formato del correo electrónico.
* `@Size`: Restringe la longitud de las cadenas.
* `@PastOrPresent`: Verifica que las fechas no sean futuras.
* `@Pattern`: Permite definir patrones de expresiones regulares (e.g., para teléfonos).

### Frontend (Formularios Reactivos)
* Validación de campos requeridos.
* Validación de formato de email y teléfono.
* Validación de fechas.

## Manejo de Errores

El backend implementa un `GlobalExceptionHandler` con `@ControllerAdvice` para capturar y manejar excepciones de manera centralizada. Los errores son formateados consistentemente en respuestas JSON, como se muestra en el siguiente ejemplo:

```json
{
  "timestamp": "2025-07-07T10:22:11",
  "status": 400,
  "errors": [
    "El nombre es obligatorio",
    "El email debe ser válido"
  ]
}
