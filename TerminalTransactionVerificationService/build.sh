#!/bin/bash
# create docker image

# Compiling and buildpacking docker image
echo "Compiling terminal-transaction-service"
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName="terminal-transaction-service_image" -DskipTests
echo "Done"