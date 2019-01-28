FROM openjdk:11
ADD ./target/muzixApplication-0.0.1-SNAPSHOT.jar  /home/user/src/muzixApplication-0.0.1-SNAPSHOT.jar
EXPOSE 8091
WORKDIR /home/user/src
ENTRYPOINT ["java","-jar","muzixApplication-0.0.1-SNAPSHOT.jar"]
