# 🏃‍♂️ FitTrack - Real-Time Fitness Tracking & Social Sharing Platform

---

## 📚 Table of Contents

- [About the Project](#about-the-project)
- [Key Features](#key-features)
- [Tech Stack](#tech-stack)
- [Architecture Overview](#architecture-overview)
- [Installation & Setup](#installation--setup)
- [Swagger API Documentation](#swagger-api-documentation)
- [JWT Authentication Flow](#jwt-authentication-flow)
- [Database Schema](#database-schema)
- [Usage Examples](#usage-examples)
- [Contributing](#contributing)
- [License](#license)

---

## 📖 About the Project

**FitTrack** is a full-stack fitness tracking and social sharing platform.  
It empowers users to track their workouts, achieve goals, participate in fitness challenges, and stay motivated through real-time notifications and community engagement.

Whether you're a casual fitness enthusiast or a hardcore athlete, **FitTrack** is built to motivate and support your fitness journey. 🏆

---

## ✨ Key Features

- 🔒 **Secure Authentication**: JWT-based authentication with Access and Refresh Tokens.
- 🏋️‍♂️ **Workout Logging**: Track exercise types, duration, and calories burned.
- 🎯 **Goal Setting**: Set and monitor personal fitness goals.
- 🏆 **Challenge Participation**: Join public challenges and compete.
- 🚀 **Real-Time Notifications**: Receive updates on goals, challenges, and achievements.
- 👨‍💻 **Admin Management**: Admin panel for user control and challenge management.
- 🛡️ **Role-Based Access Control**: User and Admin roles with separate access permissions.
- 🔄 **CRON Jobs**: Scheduled tasks for goal reminders and challenge tracking.
- 📄 **Interactive API Documentation with Swagger**.
- 🔐 **JWT Authentication** for secure communication.

---

## 🛠 Tech Stack

| Layer           | Technology                      |
|-----------------|----------------------------------|
| Backend         | Java 17, Spring Boot 3.x, Spring Security, JPA, Hibernate |
| Database        | MySQL / PostgreSQL |
| Authentication  | JWT (Access Token + Refresh Token) |
| API Documentation | Swagger / OpenAPI 3.0 |
| Frontend (Optional) | React.js, Bootstrap |

---

## 🏛️ Architecture Overview


---

## ⚙️ Installation & Setup

### Prerequisites
- Java 17+
- Maven 3.x
- MySQL or PostgreSQL
- Postman or Swagger UI for API testing

---

# Database Configuration
- spring.datasource.url=jdbc:mysql://localhost:3306/fittrackdb
- spring.datasource.username=root
- spring.datasource.password=yourpassword

# JWT Configuration
- jwt.secret=your_jwt_secret_key
- jwt.access-token.expiration=86400000   # 1 day
- jwt.refresh-token.expiration=604800000 # 7 days
---

### Clone the Repository
```bash
git clone https://github.com/your-username/fittrack.git
cd fittrack

