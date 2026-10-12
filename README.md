# 🚀 EduPredict AI - Backend REST API

> **Repository**: `student-performance-prediction-backend`  
> Robust Spring Boot 3 microservice providing authentication, student CRUD management, real-time analytics aggregation, and ML performance prediction persistence.

---

## 🛠️ Tech Stack

* **Language**: Java 17+ (Compatible with Java 17, 21, and 25)
* **Framework**: Spring Boot 3.2.5
* **Security**: Spring Security 6 with stateless JWT Bearer token authentication & BCrypt hashing
* **ORM / Database**: Spring Data JPA & Hibernate with MySQL (and embedded resilient fallback)
* **Build Tool**: Apache Maven 3.9+
* **ML Client**: Spring RestTemplate with connection/read timeouts and fallback regression handling

---

## 📋 REST API Reference

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :--- |
| `POST` | `/api/auth/login` | Authenticate user & receive JWT token | Public |
| `GET` | `/api/auth/me` | Fetch authenticated user profile | Bearer Token |
| `GET` | `/api/students` | Get all students (Supports `?search=query`) | Bearer Token |
| `GET` | `/api/students/{id}` | Get student details by ID | Bearer Token |
| `POST` | `/api/students` | Create new student record | Bearer Token |
| `PUT` | `/api/students/{id}` | Update existing student record | Bearer Token |
| `DELETE`| `/api/students/{id}` | Delete student record | Bearer Token |
| `GET` | `/api/dashboard/stats` | Aggregated dashboard statistics & category counts | Bearer Token |
| `POST` | `/api/predictions/predict` | Run ML inference, save to DB, and return advice | Bearer Token |
| `GET` | `/api/predictions` | Fetch complete prediction audit history | Bearer Token |
| `GET` | `/api/predictions/student/{id}`| Fetch prediction history for specific student | Bearer Token |
| `GET` | `/` | Informational landing page & auto-redirect to frontend | Public |

---

## ⚙️ Environment Variables

Copy `.env.example` to `.env` or set these in your cloud hosting provider:

| Variable | Description | Default (Local) |
| :--- | :--- | :--- |
| `SERVER_PORT` | HTTP Server Port | `8080` |
| `DB_URL` | JDBC MySQL Database Connection URL | `jdbc:mysql://localhost:3306/student_performance_db?createDatabaseIfNotExist=true&useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC` |
| `DB_USERNAME` | MySQL Username | `root` |
| `DB_PASSWORD` | MySQL Password | `root` |
| `ML_SERVICE_URL` | Python FastAPI ML Prediction Endpoint | `http://localhost:8001/predict` |
| `JWT_SECRET` | 256-bit HMAC SHA secret key | `404E635266556A586E3272357538782F413F4428472B4B6250645367566B5970` |
| `JWT_EXPIRATION_MS` | JWT token lifespan in milliseconds | `86400000` (24 Hours) |
| `CORS_ALLOWED_ORIGINS` | Comma-separated allowed frontend origins | `http://localhost:5173,http://127.0.0.1:5173,http://localhost:3000` |
| `CORS_ALLOWED_PATTERNS` | Origin patterns for cloud deployments (supports Vercel previews) | `https://*.vercel.app,http://localhost:[*],http://127.0.0.1:[*]` |

---

## 💻 Local Development

1. **Verify Prerequisites**:
   * Java 17 or higher (`java -version`)
   * Maven 3.8+ (`mvn -v`)
   * MySQL Server running on port 3306 (optional, embedded database activates automatically if MySQL is unreachable)

2. **Compile the project**:
   ```bash
   mvn clean compile
   ```

3. **Run the backend locally**:
   ```bash
   mvn spring-boot:run
   ```

4. **Package into an executable production JAR**:
   ```bash
   mvn clean package -DskipTests
   ```
   The runnable archive will be located at:
   `target/student-performance-prediction-1.0.0.jar`

5. **Run the packaged JAR**:
   ```bash
   java -jar target/student-performance-prediction-1.0.0.jar
   ```

---

## ☁️ Production Backend Deployment Guide

Spring Boot is a full Java application server and must run on a cloud compute container (e.g. **Render**, **Railway**, **AWS App Runner / EC2**, or **Heroku**), rather than a static site host.

### Deploying to Render.com (Recommended Free/Low Cost Option):
1. Create a new repository on GitHub: `student-performance-prediction-backend`.
2. Push this `backend/` folder contents to that repository.
3. Log in to [Render.com](https://render.com) and click **"New" ➔ "Web Service"**.
4. Connect the `student-performance-prediction-backend` repository.
5. Configure:
   * **Runtime**: `Java` (or Docker)
   * **Build Command**: `mvn clean package -DskipTests`
   * **Start Command**: `java -jar target/student-performance-prediction-1.0.0.jar`
6. Add Environment Variables:
   * `DB_URL`: JDBC URL to your managed MySQL database (e.g. Aiven, Railway MySQL, AWS RDS)
   * `DB_USERNAME`: Database user
   * `DB_PASSWORD`: Database password
   * `ML_SERVICE_URL`: URL to your deployed Python FastAPI service (e.g. `https://your-ml.onrender.com/predict`)
   * `CORS_ALLOWED_ORIGINS`: `https://your-frontend-app.vercel.app`
7. Click **Deploy Web Service**.
8. Once deployed, copy your backend service URL (e.g., `https://student-api.onrender.com`) and paste it into your Vercel frontend environment variable: `VITE_API_BASE_URL=https://student-api.onrender.com/api`.
