FROM java:8

MAINTAINER snowy<xueyuren109@gmail.com>

COPY target/*.jar /usr/local/*.jar

CMD   ["----server.port=8100"]

EXPOSE 8100

ENTRYPOINT ["java","-jar","/usr/local/*.jar"]
