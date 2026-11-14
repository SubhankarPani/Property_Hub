# 🏡 Property Hub — Property Management System

**Property Hub** is a web-based property management application built using Java and Spring Boot. It provides a platform where users can register as buyers or sellers, browse available properties, list properties for sale or rent, and contact property owners.

The application follows a **Spring MVC architecture** and uses Spring Data JPA with an H2 database for data persistence.

---

## 🚀 Overview

Finding and managing property listings can be challenging when property information and owner details are scattered across different platforms.

Property Hub provides a centralized application where:

- Buyers can browse available property listings.
- Sellers can register and publish their property details.
- Users can search properties based on location.
- Buyers can view property owner contact information.
- Sellers can manage their existing property listings.

The application provides separate workflows for buyers and sellers to simplify property discovery and management.

---

## ✨ Key Features

- **User Registration** — Create an account using username, email, password, and role.
- **Role-Based Navigation** — Directs buyers and sellers to their respective application workflows.
- **User Login and Logout** — Session-based login and logout functionality.
- **Property Listing** — Sellers can add property details for sale or rent.
- **Property Discovery** — Buyers can browse available property listings.
- **Location-Based Search** — Search properties using location names.
- **Owner Contact Details** — View property owner name, email, and phone number.
- **Seller Dashboard** — View properties listed by the logged-in seller.
- **Property Management** — Modify and delete existing property listings.
- **Responsive Web Pages** — HTML templates with CSS styling for the application interface.

---

## 🏗️ System Architecture

Property Hub follows the Model-View-Controller (MVC) architectural pattern.

```text
                   User / Browser
                         |
                         v
                  Thymeleaf Views
                         |
                         v
                  Spring MVC Layer
                    Controllers
                         |
                         v
                   Service Layer
                  PropertyService
                         |
                         v
                 Repository Layer
               Spring Data JPA
                         |
                         v
                    H2 Database
```

### Application Layers

| Layer | Responsibility |
|---|---|
| Controller | Handles HTTP requests, user navigation, and form submissions |
| Service | Contains property management operations |
| Repository | Performs database operations using Spring Data JPA |
| Model | Defines User and Property entities |
| View | Renders HTML pages using Thymeleaf |

---

## 🧩 Application Workflows

### 1. User Registration

Users can create an account by providing:

- Username
- Email
- Password
- Role (Buyer or Seller)

The application checks whether the email is already registered before saving the user.

After successful registration:
- Sellers are redirected to the property details form.
- Buyers are redirected to the property listing page.

### 2. User Authentication

Registered users can log in using their email and password.

The application:
- Checks whether the user exists.
- Verifies the submitted password against the stored password.
- Creates an HTTP session containing the user's email.
- Redirects users based on their registered role.

Users can log out, which invalidates the current session.

### 3. Property Listing — Seller Workflow

Sellers can submit property information, including:

- Property name
- Property description
- Property type (SELL or RENT)
- Price
- Location
- Owner name
- Owner email
- Owner phone
- Property image reference

The application associates the property with the logged-in seller's email and saves the property through the service and repository layers.

### 4. Property Discovery — Buyer Workflow

Buyers can:
- View available property listings.
- Search properties by location.
- Browse property descriptions, prices, and property types.
- View owner contact details for a selected property.

Location search uses a case-insensitive partial match.

### 5. Seller Property Management

Sellers can access their own property listings.

Available operations include:

- View previously listed properties.
- Modify property information.
- Delete a property listing.

The controller checks the logged-in seller's email against the property's owner email before allowing modification or deletion.

---

## 🛠️ Technology Stack

| Component | Technologies |
|---|---|
| Programming Language | Java 17 |
| Backend Framework | Spring Boot 3.4.0 |
| Web Framework | Spring MVC |
| ORM / Persistence | Spring Data JPA, Hibernate |
| Database | H2 Database |
| Frontend | HTML, CSS |
| Template Engine | Thymeleaf |
| Build Tool | Apache Maven |
| Session Management | HTTP Session |
| Testing Framework | JUnit / Spring Boot Test |

---

## 📂 Project Structure

```text
Property_Hub/
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── property_hub/
│   │   │       └── property_management/
│   │   │           │
│   │   │           ├── controller/
│   │   │           │   ├── IndexController.java
│   │   │           │   ├── OwnerDetailsController.java
│   │   │           │   ├── PropertyController.java
│   │   │           │   └── PropertyListingController.java
│   │   │           │
│   │   │           ├── model/
│   │   │           │   ├── User.java
│   │   │           │   └── Property.java
│   │   │           │
│   │   │           ├── repository/
│   │   │           │   ├── UserRepository.java
│   │   │           │   └── PropertyRepository.java
│   │   │           │
│   │   │           ├── service/
│   │   │           │   └── PropertyService.java
│   │   │           │
│   │   │           └── PropertyManagementApplication.java
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       ├── templates/
│   │       │   ├── index.html
│   │       │   ├── login.html
│   │       │   ├── signup.html
│   │       │   ├── propertyForm.html
│   │       │   ├── propertyListing.html
│   │       │   ├── sellerPropertyList.html
│   │       │   ├── ownerDetails.html
│   │       │   └── thankyou.html
│   │       │
│   │       └── application.properties
│   │
│   └── test/
│
├── data/
├── h2db/
├── uploads/
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

## ⚙️ Getting Started

### Prerequisites

Install the following before running the application:

- Java Development Kit (JDK) 17 or later
- Apache Maven (optional, as Maven Wrapper is included)
- IntelliJ IDEA, Eclipse, or another Java IDE

### 1. Clone the Repository

```bash
git clone https://github.com/SubhankarPani/Property_Hub.git

cd Property_Hub
```

### 2. Configure the Database

The application uses an H2 file-based database.

Database configuration is available in:

```text
src/main/resources/application.properties
```

Current configuration:

```properties
spring.application.name=property_management

spring.datasource.url=jdbc:h2:file:C:/h2db/user_data
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=update

spring.h2.console.enabled=true
spring.h2.console.path=/h2
```

**Note:** The configured database path is Windows-specific. Update `spring.datasource.url` if you are running the project on another operating system or want to use a different database location.

### 3. Run the Application

Using Maven Wrapper on Windows:

```bash
mvnw.cmd spring-boot:run
```

Using Maven Wrapper on macOS/Linux:

```bash
./mvnw spring-boot:run
```

Alternatively:

```bash
mvn spring-boot:run
```

### 4. Open the Application

Once the application starts, open:

```text
http://localhost:8080
```

H2 Database Console:

```text
http://localhost:8080/h2
```

Use the JDBC URL configured in `application.properties` when connecting through the H2 console.

---

## 🔌 Application Routes

| Method | Endpoint | Description |
|---|---|---|
| GET | `/` | Displays the landing page |
| GET | `/signup` | Displays the registration page |
| POST | `/register` | Registers a new user |
| GET | `/login` | Displays the login page |
| POST | `/login` | Authenticates a user |
| GET | `/logout` | Invalidates the user session |
| GET | `/property-form` | Displays the property form |
| POST | `/submit-property` | Saves a new property |
| GET | `/property-listing` | Displays property listings |
| GET | `/seller-property-list` | Displays the seller's properties |
| GET | `/modify-property/{id}` | Displays the property modification form |
| POST | `/update-property` | Updates property information |
| POST | `/delete-property/{id}` | Deletes a property |
| GET | `/owner-details/{id}` | Displays property owner details |

---

## 🗄️ Database Design

The application uses two primary JPA entities.

### User Entity

Stores registered user information.

| Field | Description |
|---|---|
| id | Primary key |
| username | User's name |
| email | User's email address |
| password | User's password |
| role | Buyer or Seller |

### Property Entity

Stores property listing information.

| Field | Description |
|---|---|
| id | Property primary key |
| propertyName | Name of the property |
| propertyDescription | Property description |
| price | Property price |
| location | Property location |
| ownerName | Owner's name |
| ownerEmail | Owner's email |
| ownerPhone | Owner's phone number |
| propertyType | SELL or RENT |
| propertyImage | Property image reference |

### Repository Layer

The application uses Spring Data JPA repositories:

- `UserRepository` — Provides user persistence and email-based lookup.
- `PropertyRepository` — Provides property persistence, owner-based lookup, and location-based search.

Custom repository methods include:

```java
findByEmail(String email)

findByOwnerEmail(String ownerEmail)

findByLocationContainingIgnoreCase(String location)
```

---

## 🔮 Future Enhancements

Potential improvements for future versions include:

- Password hashing and stronger authentication.
- Spring Security integration with role-based authorization.
- Property image upload and storage management.
- Advanced property filters for price, type, and location.
- Property sorting and pagination.
- Property favourites and saved listings.
- REST API integration for a separate frontend.
- Improved validation and exception handling.
- Automated unit and integration testing.
- Deployment using a production database.

---

## ⚠️ Project Notes

- This is a Spring Boot MVC application using server-rendered Thymeleaf pages.
- The current login implementation compares submitted passwords directly with stored values; password hashing should be implemented before production use.
- The property form contains an image file input, while the current backend model stores an image reference as a string. A complete multipart upload workflow is not implemented in the reviewed code.
- The current H2 database configuration uses a local file path.
- The Maven Surefire configuration currently skips test execution.

---

## 👨‍💻 Author

**Subhankar Pani**

GitHub: [@SubhankarPani](https://github.com/SubhankarPani)

Project Repository: [Property_Hub](https://github.com/SubhankarPani/Property_Hub)

---

⭐ If you find this project interesting, feel free to explore the repository and share feedback.

**Property Hub — Simplifying Property Discovery and Management.**
