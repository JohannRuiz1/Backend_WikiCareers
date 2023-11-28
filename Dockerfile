FROM openjdk:17

COPY target/*.jar /var/wikicareers/wikicareers-backend.jar

WORKDIR /var/wikicareers/

EXPOSE 8080

ENTRYPOINT ["java","-jar","wikicareers-backend.jar"]
