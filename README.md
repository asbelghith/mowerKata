### Versions Used
Java: JDK 22
Spring Boot: 3.2.5
Maven: 3.2.2

NB: when executing the project, the output.txt file should be generated under src/ containing results

## Building and Deployment
### Build Instructions
To build the project, make sure you have JDK 22 and Maven installed and that terminal pointing to root directory. Then run the following command:

bash -> mvn clean install
bash -> mvn spring-boot:run

by executing this series of commands, an output.txt file should be generated under the root directory containing results of processing.

### Running with Docker
To run the application using Docker, follow these steps:

* Build the Docker image: (run it in the root folder where the dockerfile exists)
  bash -> docker build -t kata-mower .
* Run the Docker container :
  docker run -d -p 8080:8080 -v /Users/ahmedsalembelguith/Desktop/Projects/workspace/tondeuse-project/src/main/resources/nominalCase.txt:/app/nominalCase.txt --name kata-mower-container kata-mower:latest

This will mount the input and output files from your local filesystem into the Docker container and run the kata-mower application.

To stop and remove the Docker container and image
bash -> docker stop lawnmower-container
bash -> docker rm lawnmower-container
