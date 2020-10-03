FROM java:8

COPY target/snowy-samuume-blog-1.0.jar /usr/local/snowy-samuume-blog-1.0.jar

CMD   ["----server.port=8100"]

EXPOSE 8100

ENTRYPOINT ["java","-jar","/usr/local/snowy-samuume-blog-1.0.jar"]
