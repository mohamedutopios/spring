mvn spring-boot:run -Dspring-boot.run.profiles=dev

mvn spring-boot:run -Dspring-boot.run.profiles=prod

mvn clean package

java -jar target/*.jar --spring.profiles.active=prod

Config + DatabaseService => profile sur methode

DevEmail + ProdEmail + EmailService => profile sur class.

Pour le depoiement : 

- mvn spring-boot:build-image
- mvn spring-boot:build-image -Dspring-boot.run.profiles=prod

docker run -d -p 9090:8081 first-project:0.0.1-SNAPSHOT
