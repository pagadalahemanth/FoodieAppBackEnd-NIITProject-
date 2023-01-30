# need openjdk
FROM openjdk

# work directory
WORKDIR usr/lib

# jars
ADD ./target/Api-Gateway-App-0.0.1-SNAPSHOT.jar /usr/lib/Api-Gateway-App-0.0.1-SNAPSHOT.jar

# entry point
ENTRYPOINT  ["java","-jar","Api-Gateway-App-0.0.1-SNAPSHOT.jar"]