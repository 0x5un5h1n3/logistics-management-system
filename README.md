## Logistics Management System

A comprehensive Java EE application for managing logistics operations, including shipments, cargos, and vehicles. This system provides functionalities for creating, updating, and tracking shipments, associating cargos with shipments, registering and managing vehicles, and optimizing routes for efficient transportation.

### Key Features

- Shipment Management: Create, update, and cancel shipments with details such as origin, destination, and shipping date.
- Cargo Management: Associate cargos with shipments, including cargo description and weight.
- Vehicle Management: Register, update, and deregister vehicles with information like type, license plate, and capacity.
- User Authentication and Authorization: Secure user authentication and role-based access control for different application functionalities.
- Exception Handling and Logging: Robust exception handling and logging mechanisms for better error management and debugging.
- Database Integration: Seamless integration with a relational database (e.g., MySQL) for persistent data storage.
- Jakarta EE Architecture: Follows the Jakarta EE (formerly Java EE) architecture, utilizing technologies like Jakarta EJB, Jakarta Persistence, and Jakarta Servlets.

### Technologies Used

- Java 11: The project is written in Java 11, as specified in the pom.xml files of the ear and web modules.
- Jakarta EE 10 (formerly Java EE): The project follows the Jakarta EE 10 (formerly Java EE) architecture, utilizing technologies like Jakarta EJB, - Jakarta Persistence, and Jakarta Servlets. This is evident from the dependencies in the pom.xml files, such as jakarta.jakartaee-web-api and jakarta.ejb-api.
- Jakarta EJB: The project uses Jakarta Enterprise JavaBeans (EJB) for implementing business logic and services. The ejb module contains the EJB components.
- Jakarta Persistence (JPA): The project uses Jakarta - Persistence API (JPA) for object-relational mapping and database integration. The ejb module includes JPA entity classes and persistence configuration.
- Jakarta Servlets: The project uses Jakarta Servlets for handling HTTP requests and responses. The web module contains servlet classes.
- JSP: The project uses JavaServer Pages (JSP) for generating dynamic web content. The web module includes JSP files.
- JAX-RS: The project uses JAX-RS (Jakarta RESTful Web Services) for implementing RESTful web services. The web module contains resource classes annotated with JAX-RS annotations.
- MySQL: The project uses MySQL as the relational database management system, as indicated by the mysql-connector-java dependency in the ejb module's pom.xml file.
- Maven: The project uses Apache Maven for build automation and dependency management, as evident from the pom.xml files in the ear, ejb, and web modules.
- Hibernate: The project uses Hibernate as the JPA implementation provider, as indicated by the hibernate-entitymanager dependency in the ejb module's pom.xml file.
- JUnit 5: The project includes JUnit 5 as a dependency for writing and running unit tests, as shown in the ejb module's pom.xml file.
- SLF4J: The project includes the SLF4J (Simple Logging Facade for Java) API as a dependency, which is a logging framework.
- HTML/CSS/JavaScript: The project uses HTML, CSS, and JavaScript for building the user interface and providing client-side functionality, as seen in the JSP files and the js directory.

<!-- GETTING STARTED -->

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 11: The project is built using Java 11, as specified in the pom.xml files of the ear and ejb modules. You need to have JDK 11 installed on your system.
- Apache Maven: The project uses Apache Maven for build automation and dependency management. You need to have Maven installed on your system.
- Jakarta EE 10 (formerly Java EE) Application Server: Since this is a Jakarta EE application, you need to have a compatible application server installed. Some popular options include:

  - GlassFish
  - Payara
  - WildFly
  - Apache TomEE

- MySQL Database: The project uses MySQL as the relational database management system. You need to have MySQL installed and configured on your system or a remote MySQL server accessible from your development environment.
- MySQL Connector/J: The project includes the mysql-connector-java dependency in the ejb module's pom.xml file. This connector is required to establish a connection between the application and the MySQL database.
- Integrated Development Environment (IDE): While not strictly required, an IDE like IntelliJ IDEA, Eclipse, or NetBeans can greatly simplify the development and deployment process for Jakarta EE applications.
- Database Configuration: You need to configure the database connection details in the application server or provide the necessary configuration files for setting up the data source. The persistence.xml file in the project expects a data source named jdbc/logistics_db to be configured in the application server.
- Application Server Configuration: Depending on the application server you choose, you may need to configure additional settings, such as data sources, security realms, or resource adapters, to ensure the proper deployment and execution of the logistics management system.
- Build and Deploy: After setting up the prerequisites, you need to build the project using Maven and deploy the generated EAR file to the application server. The deployment process may vary depending on the application server you are using.

This logistics management system is designed to streamline and optimize logistics operations, providing a centralized platform for managing shipments, cargos, and vehicles efficiently.
