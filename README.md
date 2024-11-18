# Device Management Service

## Overview

The Device Management Service is a Spring Boot project that provides a RESTful interface for managing devices. It uses an H2 in-memory database for data storage and supports CRUD operations, along with a feature to retrieve devices by Brand.

## Technologies Used

Java 17 (or later)  
Spring Boot 3.3.5  
Spring Data JPA  
H2 Database (In-Memory)  
Lombok (for boilerplate code reduction)  
Maven (for dependency management)  

## API Endpoints

| HTTP Method | Endpoint                            | Description                                           |
|-------------|-------------------------------------|-------------------------------------------------------|
| `POST`      | `/devices/addDevice`                | Create/Add a new device                               |
| `GET`       | `/devices/getAll`                   | Get a list of all devices                             |
| `GET`       | `/devices/{deviceId}`               | Retrieve details of a specific device using device id |
| `PUT`       | `/devices/update/{deviceId}`        | Update an existing device                             |
| `DELETE`    | `/devices/delete/{deviceId}`        | Delete a specific device                              |
| `GET`       | `/devices/getByBrand?brand={value}` | Retrieve devices by brand                             |


## Database Configuration  
This project uses an H2 in-memory database, which resets each time the application restarts. The database schema and data are automatically managed by Spring JPA.

### H2 Console  

URL: http://localhost:8080/h2-console  
JDBC URL: jdbc:h2:mem:devicemanagementdb  
Username: sa  
Password: (leave blank)  
  
### Customization  

Switch to an External Database  
To use an external database (e.g., MySQL or PostgreSQL), update the application.properties file with your database connection details:  

### properties

spring.datasource.url=jdbc:mysql://localhost:port/devicemanagement  
spring.datasource.username=<your-username>  
spring.datasource.password=<your-password>  
spring.jpa.hibernate.ddl-auto=update  

### Prerequisites  

JDK 17 or later  
Maven 3.6 or later  

### Run the Application  

Use Maven to build and run the project:  

##### mvn spring-boot:run  

Alternatively, package the application and run the JAR:  

##### mvn clean package  

##### java -jar target/device-management-api-0.0.1-SNAPSHOT.jar  

## Test the API  

Use tools like Postman or curl to test the endpoints.  

Examples:  

#### Create a Device:  

curl -X POST 'http://localhost:8080/devices/addDevice' \
--header 'Content-Type: application/json' \
--data '{"deviceName": "Mobile model 123", "brand": "BrandXYZ"}'

#### Get All Devices:  

curl -X GET 'http://localhost:8080/devices/getAll'

#### Get Device By Id:  

curl -X GET 'http://localhost:8080/devices/2'

#### Update a Device:  

curl -X PUT 'http://localhost:8080/devices/update/1' \
--header 'Content-Type: application/json' \
--data '{
"brand":"Sony"
}'

#### Retrieve Device by Brand:  

curl -X GET 'http://localhost:8080/devices/getByBrand?brand=Apple'

#### Delete a Device  

curl -X DELETE 'http://localhost:8080/devices/delete/3'  

### Acknowledgements

Spring Boot Documentation: https://spring.io/projects/spring-boot  
H2 Database Documentation: http://www.h2database.com/  

