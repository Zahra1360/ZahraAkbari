FROM openjdk:8
ADD target/docker-product.jar  docker-product.jar
EXPOSE 8090
ENTRYPOINT ["java","-jar","docker-product.jar"]
