# 🏢 HR Management System (HRMS)

A modern, full-stack Human Resource Management System built with **Spring Boot 3 + MySQL 8.0** (Backend), **Next.js 14 + React + Tailwind CSS** (Frontend), and **Docker & Docker Compose** (Container Infrastructure).

> 📖 **Comprehensive End-to-End Documentation:** For a deep dive into system architecture, sequence diagrams, container networking (`hrms-network`), internal DNS (`db:3306`), authentication flows, and data seeding lifecycles, please see **[DOCUMENTATION.md](DOCUMENTATION.md)**.

---

## 🗂️ Project Structure & Key Files

```
Hr-Management-System/
├── .env                     # Centralized environment configuration (Ports, DB credentials, JWT secrets)
├── .env.sample              # Canonical environment template for local setup
├── docker-compose.yml       # Multi-container orchestration & bridge networking
├── DOCUMENTATION.md         # Full technical architecture & workflow documentation
├── backend/hrms/            # Spring Boot 3.2.5 REST API Backend (Java 17, JWT, Hibernate JPA)
│   └── Dockerfile           # Multi-stage build (Maven compiler -> Alpine JRE 17 runner)
└── frontend/                # Next.js 14 / React Web Application (Tailwind CSS, Axios)
    └── Dockerfile           # Multi-stage build (Deps -> Next.js standalone builder -> runner)
```

---

## 🚀 Quick Start Guide (One-Command Deployment)

The entire three-tier application (Next.js Frontend, Spring Boot API, and MySQL Database) is containerized and pre-configured to run seamlessly across a shared Docker bridge network (`hrms-network`).

### 1️⃣ Environment Setup
Copy the sample environment file to create your local `.env` configuration:
```powershell
Copy-Item .env.sample .env
```
*(Optionally adjust `DB_PASSWORD` or `SEED_*_PASSWORD` inside `.env` if desired).*

### 2️⃣ Launch All Containers
Run Docker Compose in detached mode to build and start the entire stack:
```powershell
docker compose up -d --build
```

### 3️⃣ Access Your Application
Once `docker compose ps` reports all containers as healthy and running:
* **💻 Frontend Web UI:** [http://localhost:3000](http://localhost:3000)
* **⚙️ Backend API / Tomcat:** [http://localhost:8080](http://localhost:8080)
* **🗄️ MySQL Database Host:** `localhost:3306` (from external tools like DBeaver/Workbench) or `db:3306` (inside Docker)

---

## 🔐 Pre-Seeded Default Accounts (`DataSeeder`)

When the backend container boots up, `DataSeeder.java` securely checks the database (`existsByEmail`) and automatically seeds three initial test accounts if they do not already exist:

| Role | Email | Password (Default / Overridable via `.env`) | Login Portal (`loginType`) |
|------|-------|-------------------------------------------|---------------------------|
| **ADMIN** | `admin@hrms.com` | `Admin@123` *(via `SEED_ADMIN_PASSWORD`)* | `ADMIN` |
| **HR** | `hr@hrms.com` | `Hr@12345` *(via `SEED_HR_PASSWORD`)* | `HR` |
| **EMPLOYEE** | `emp@hrms.com` | `Emp@12345` *(via `SEED_EMPLOYEE_PASSWORD`)* | `EMPLOYEE` |

> *Note: Plaintext passwords are never logged to console output during seeding for enterprise security.*

---

## 💻 Local Development (Without Docker for Backend)

If you prefer developing and debugging Spring Boot code locally inside your IDE (IntelliJ / Eclipse / VS Code):

1. **Start only the database container:**
   ```powershell
   docker compose up -d db
   ```
2. **Run Spring Boot using the `local` profile:**
   ```powershell
   cd backend\hrms
   mvn spring-boot:run -Dspring-boot.run.profiles=local
   ```
   *(This connects to either local H2 embedded DB or your `localhost:3306` instance as configured in `application-local.properties`).*

---

## 📚 Useful Maintenance Commands

```powershell
# View real-time logs for the Spring Boot backend
docker compose logs -f backend

# View status and health checks of all running services
docker compose ps

# Gracefully stop all containers
docker compose stop

# Completely tear down containers, networks, and volumes (⚠️ resets database data)
docker compose down -v
```

---

## 🔗 Further Reading & Architecture Reference
* 📘 **End-to-End System Architecture & Networking:** [DOCUMENTATION.md](DOCUMENTATION.md)
* 📙 **Backend API Details & Swagger Details:** [backend/hrms/README.md](backend/hrms/README.md)
