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

## Run the Application  

Use Maven to build and run the project:  

##### mvn spring-boot:run  

Alternatively, package the application and run the JAR: 
  
Build the JAR file: Ensure the JAR file is created in the target directory using the Maven package command:  
##### mvn clean package  
##### java -jar target/device-management-api-0.0.1-SNAPSHOT.jar

### Running the Docker Container
mvn clean package generates the device-management-service-0.0.1-SNAPSHOT.jar file in the target folder.

Build the Docker image:  
Run the following command from the project root directory (where the Dockerfile is located):
##### docker build -t device-management-service:1.0

Run the container using the built image:
##### docker run -p 8080:8080 device-management-service:1.0

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


#### Building the Docker Image  



mvn clean package

bash
Copy code
.
Verify the Docker image:

bash
Copy code
docker images
Running the Docker Container


docker run -p 8080:8080 device-management-service:1.0
The application will be accessible at http://localhost:8080.
### Acknowledgements

Spring Boot Documentation: https://spring.io/projects/spring-boot  
H2 Database Documentation: http://www.h2database.com/  

