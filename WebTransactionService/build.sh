#!/bin/bash
# create docker image

# Compiling and buildpacking docker image
echo "Compiling web-transaction-service"
mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName="web-transaction-service_image"
echo "Done"