# Buy Now Pay Later

This is a **Buy Now Pay Later (BNPL)** application that allows clients to register, make purchases, and retrieve client details through a RESTful API secured with JWT authentication.

## 🚀 Requirements

Make sure the following are installed on your system before running the project:

- Java 17
- Maven
- Docker

## 🛠️ Running the Application

Follow these steps to build and run the application:

1. **Build the project:**

   ```bash
   mvn clean package

2. **Start the application using Docker Compose:**

    ```bash
   docker compose up

3. **Open your browser and go to the Swagger UI:**

    ```bash
   http://localhost:6868/swagger-ui/index.html#/
   
## 🔐 Authentication Guide

Before accessing other endpoints, you need to authenticate:

1. Navigate to the `POST /auth/token` endpoint in Swagger.
2. Enter your **username** and execute the request.
3. Copy the **Bearer JWT token** from the response.
4. Click the **"Authorize"** button on the top right of Swagger UI.
5. Paste the token and LogIn.

You are now authorized to use all protected endpoints.

---

## 📚 API Usage Flow

Here’s the recommended user journey to interact with the system:

1. **Create a client**  
   Use the `POST /client` endpoint with client details.

2. **Make a purchase**  
   Use the `POST /purchase` endpoint with the `clientId` and purchase details.

3. **Get client details**  
   Use the `GET /client/{clientId}` endpoint to view the client’s information and purchase history.

---

## 🧩 Endpoints Overview

- `POST /auth/token` – Generate JWT token
- `POST /client` – Create a new client
- `POST /purchase` – Record a purchase for a client
- `GET /client/{clientId}` – Retrieve client and associated purchase details