#!/bin/bash
# launch multiple docker containers depending on the given parameter and let nginx load balance the requests

if [ $# -ne 1 ]; then
    echo "Usage: $0 <number-of-instances>"
    exit 1
fi

docker-compose up --scale web-transaction-docker-service=$1