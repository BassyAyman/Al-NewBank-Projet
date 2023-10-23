#!/bin/bash
# launch multiple docker containers depending on the given parameter and let nginx load balance the requests

if [ $# -ne 1 ]; then
    echo "Usage: $0 <number-of-instances of terminal transaction service>"
    exit 1
fi

docker-compose up --scale terminal-transaction-docker-service=$1
