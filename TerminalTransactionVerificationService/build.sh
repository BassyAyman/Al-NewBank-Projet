#!/bin/bash
# create docker image

# Build docker image
echo "Building terminal-transaction-service image"
#mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName="terminal-transaction-service_image" -DskipTests

docker build -t terminal-transaction-service .

echo "Done"