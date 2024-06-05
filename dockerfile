FROM openjdk:22-rc-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY target/kata-mower-1.0.0.jar /app/kata-mower.jar

# Copy the nominalCase.txt file into the container
COPY src/main/resources/nominalCase.txt /app/nominalCase.txt

EXPOSE 8080

# Command to run the application
CMD ["java", "-jar", "kata-mower.jar"]