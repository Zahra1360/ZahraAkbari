## Product management
This project implements product management project to CRU command 

## Technologies, Framework and Tools
-	Java 8
-	Spring Boot
-	H2 (in memory)
-	JUnit
-   Mockito
-	Maven
-   Docker

## Project Structure
I have utilized maven as a build tool. 
Source codes are placed in this path: “product/src/maun/java” and also some files will be generate in this path: “product\target\”
I have designed the project based on SOLID principals.


## Data and Production Ready 
I would like to mention I have used h2 as a database but as a product-ready code we can replace configuration of other database like 
PostgreSQL.
In this step I have concentrated on code quality for production. 
Also I put a data.sql file to migrate sample data to the current database (PRODUCT table) , in large scale migration in production environment 
we need to apply other policy to do.

## Document
Project document is available as a Javadoc under the “javadoc” folder. Besides, you can access the API’s resources documentation via Swagger UI. 

## Test
I have written some useful test classes under the “product/src/test/java” folder which included service test and controller test. I utilized spring-boot-test and JUnit and MockMvc and MockBean to cover unit testing and integration testing.

use "mvn test" to run all tests 

## Run
This project can be run by three ways:
1.  Build and Run DockerFile in "product" folder by
## create an image
# docker build -f Dockerfile -t docker-product
## to see images
# docker images
## to run the image
# docker run -p 8090:8090 docker-product
## to see all container
# docker container ls -a
## to stop the container
# docker container stop ${container ID} 
## to remove the container
# docker container rm ${container ID} 

2.	Run controller module (e com.asellion.product.ProductApplication class) from your IDE.
3.	Build controller module in "product" folder by 

#####mvn clean 
#####mvn compile
#####mvn install 
and then run generated *.jar file by 
java -jar docker-product.jar



Web service URL to create a new product **http://localhost:8090/api/product** 
Web service URL to update a product **http://localhost:8090/api/products/{id}**
Web service URL to get a product **http://localhost:8090/api/products/{id}**
Web service URL to get all products **http://localhost:8090/api/products**
H2 console **http://localhost:8090/h2**
 

After running the project it can be used by any rest client or swagger.
You can open Swagger by this link: **http://localhost:8090/swagger-ui.html**

"# ZahraAkbari" 
