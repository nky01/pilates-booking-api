# Pilates Booking API 🧘‍♀️✨

<p align="left">
  <img src="https://img.shields.io/badge/JAVA_21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 21" />
  <img src="https://img.shields.io/badge/SPRING_BOOT-6DB33F?style=for-the-badge&logo=springboot&logoColor=white" alt="Spring Boot" />
  <img src="https://img.shields.io/badge/GIT-F05032?style=for-the-badge&logo=git&logoColor=white" alt="Git" />
</p>

(en desarrollo) API RESTful desarrollada con **Java 21** y **Spring Boot 3** para la gestión integral de reservas, cupos y horarios en un estudio de Pilates.

---

## 🚀 Características Principales

- **Gestión de Alumnos (`Student`)**: Registro y administración de perfiles de usuario.
- **Catálogo de Clases (`PilatesClass`)**: Definición de modalidades (Reformer, Mat, etc.), duración y capacidad máxima.
- **Programación de Horarios (`Schedule`)**: Control de turnos, horarios de inicio/fin y disponibilidad de cupos en tiempo real.
- **Sistema de Reservas (`Booking`)**: Procesamiento de reservas vinculando alumnos con horarios disponibles.

---

## 🛠️ Tecnologías Utilizadas

- **Lenguaje:** Java 21
- **Framework:** Spring Boot 3.x (Spring Data JPA, Spring Web)
- **Base de Datos:** PostgreSQL
- **Herramientas de Productividad:** Lombok
- **Control de Versiones:** Git & GitHub

---

## 📊 Modelo Entidad-Relación (ER)

El modelo de datos relacional optimizado para la gestión de cupos y transacciones:

<img width="50%" alt="image" src="https://github.com/user-attachments/assets/64220588-5b89-464e-a26b-259f06761091" />

---

## ⚙️ Configuración e Instalación
Clonar repositorio:

```
git clone https://github.com/nky01/pilates-booking-api.git
```

Levantar PostgreSQL con Docker:
```
docker compose up -d
```

Ejecutar la aplicación:
```
./mvnw spring-boot:run
```

## 👩‍💻 Autora

* **Nicole Belen Cayo** — Backend Developer
* **GitHub:** [@nky01](https://github.com/nky01)
