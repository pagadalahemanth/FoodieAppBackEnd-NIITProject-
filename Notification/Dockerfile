# need openjdk
FROM openjdk

# work directory
WORKDIR usr/lib

# jars
ADD ./target/Notification-0.0.1-SNAPSHOT.jar /usr/lib/Notification-0.0.1-SNAPSHOT.jar

# entry point
ENTRYPOINT  ["java","-jar","Notification-0.0.1-SNAPSHOT.jar"]