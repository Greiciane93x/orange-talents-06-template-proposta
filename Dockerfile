FROM openjdk:11
MAINTAINER Ane Assis
WORKDIR proposta
EXPOSE 8080
ADD target/*.jar /proposta/proposta.jar
ENTRYPOINT ["java", "-jar", "proposta.jar"]