# need openjdk
FROM openjdk

# work directory
WORKDIR usr/lib

# jars
ADD ./target/FoodieApp-0.0.1-SNAPSHOT.jar /usr/lib/FoodieApp-0.0.1-SNAPSHOT.jar

# entry point
ENTRYPOINT  ["java","-jar","FoodieApp-0.0.1-SNAPSHOT.jar"]