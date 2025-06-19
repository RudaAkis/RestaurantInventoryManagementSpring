# RestaurantInventoryManagementSpring
🍽️ Restaurant Inventory Manager - Backend (Spring Boot)
🚀 About the Project
Restaurant Inventory Manager system for tracking products, vendors, categories, units, and dishes.
Supports full CRUD, search, filtering, refilling stock, and preparing dishes (removing product quantities).

✅ Key Features:
Full CRUD: Products, Dishes, Vendors, Categories, Units.

Search & Filter: By vendor, category, and expiry date.

Product Refill: Adjust quantity and expiry to simulate new batches.

Prepare Dishes: Deduct ingredients based on dish quantity.

Security: JWT-based authentication.

Role System:

Admin: Full access (view, edit, delete)

Manager: View & edit

User: View & prepare dishes

Swagger UI: Fully set up and grouped, authorization works directly in Swagger.

🔮 Future feature: Batch tracking with FIFO (first in, first out) for accurate stock rotation.

🛠️ Technologies Used:
Java 21

Spring Boot 3

MySQL

Spring Security (JWT)

Swagger (OpenAPI)

▶️ How to Run the Backend:
Clone the repo:

bash
Copy
Edit
git clone <your-backend-repo-link>
Configure application.properties:

properties
Copy
Edit
spring.datasource.url=jdbc:mysql://localhost:3307/your-database-name
spring.datasource.username=your-username
spring.datasource.password=your-password
Run the project from IntelliJ using the main class:

bash
Copy
Edit
RestaurantInventoryManagerApplication
Swagger available at:

bash
Copy
Edit
http://localhost:8080/swagger-ui/index.html