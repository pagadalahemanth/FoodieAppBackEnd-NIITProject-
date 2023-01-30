# need openjdk
FROM openjdk

# work directory
WORKDIR usr/lib

# jars
ADD ./target/UserAuthentication-0.0.1-SNAPSHOT.jar /usr/lib/UserAuthentication-0.0.1-SNAPSHOT.jar

# entry point
ENTRYPOINT  ["java","-jar","UserAuthentication-0.0.1-SNAPSHOT.jar"]