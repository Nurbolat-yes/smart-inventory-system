# Smart Inventory System

Smart Inventory System is a web application designed to manage products, warehouses, and stock levels. The system allows users to track inventory in real time, receive alerts when stock is low, and analyze basic inventory data.

## Project Overview

The main goal of this project is to provide a simple and structured inventory management solution for small or medium businesses. It combines a backend built with Spring Boot and a basic frontend interface for interaction.

The system supports authentication, product management, warehouse tracking, stock updates, and analytics.

## Features

- User authentication (login and registration using JWT)
- Product management (create and view products)
- Warehouse management (create and view warehouses)
- Stock tracking and updates
- Low stock alerts
- Basic analytics
- Simple frontend interface connected to backend

## Technology Stack

Backend:
- Java (Spring Boot)
- REST API
- JWT Authentication
- H2 / SQL Database

Frontend:
- HTML
- CSS
- JavaScript (Vanilla)

## System Architecture

Frontend communicates with the backend through REST API requests.  
The backend processes the logic and interacts with the database.

Frontend → REST API → Backend → Database

Authentication is handled using JWT tokens.

## Project Structure

smart-inventory-system/

- src/main/java/...        (backend logic)
- src/main/resources/      (configuration files)
- frontend/                (frontend interface)
  - index.html

- README.md

## How to Run

1. Run the backend

Open the project in an IDE (IntelliJ or VS Code) and run:

SmartInventoryApplication.java

The server will start at:
http://localhost:8080

2. Open the frontend

Open the HTML file in a browser:

frontend/index.html

If the file is placed inside the Spring Boot static folder:
src/main/resources/static/index.html

then it can be accessed via:
http://localhost:8080/

## API Endpoints (example)

POST /auth/register — register a new user  
POST /auth/login — login  
GET /products — get all products  
POST /products — create product  
GET /warehouses — get warehouses  
POST /warehouses — create warehouse  
POST /stock — update stock  
GET /alerts — get low stock alerts  
GET /analytics — get analytics data  

## User Roles

Admin — full access  
Manager — manage products and stock  
User — view data  

## Project Status

Backend — completed  
Authentication — completed  
Database — completed  
Frontend — basic version  
Analytics — in progress  

## Roadmap

Phase 1:
- Backend API
- Authentication
- Database setup

Phase 2:
- Frontend integration
- UI improvements

Phase 3:
- Notifications
- Advanced analytics
- Mobile version

## Known Issues

- Frontend is very basic
- Some API responses may require adjustment
- Error handling is limited

## Future Improvements

- Add charts for analytics
- Improve UI/UX
- Add role-based interface
- Implement real-time updates
- Deploy using Docker

## Team

Project Manager — Ayaulym  
Backend Developer — team member  
Frontend Developer — team member  

## Conclusion

This project demonstrates a full workflow from backend development to frontend integration. It also includes basic project management structure and planning.