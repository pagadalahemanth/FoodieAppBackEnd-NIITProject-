# need openjdk
FROM openjdk

# work directory
WORKDIR usr/lib

# jars
ADD ./target/EurekaServer-0.0.1-SNAPSHOT.jar /usr/lib/EurekaServer-0.0.1-SNAPSHOT.jar

# entry point
ENTRYPOINT  ["java","-jar","EurekaServer-0.0.1-SNAPSHOT.jar"]