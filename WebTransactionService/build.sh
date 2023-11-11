#!/bin/bash
# create docker image

# build docker image
echo "build web-transaction-service image"
#mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName="web-transaction-service_image" -DskipTests

docker build -t web-transaction-service .

echo "Done"