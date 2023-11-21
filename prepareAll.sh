#!/bin/bash

set -e

if [ $# -lt 2 ]; then
    echo "Usage: $0 <number-of-instances of terminal transaction service> <number-of-instances of web transaction service>"
    exit 1
fi

function compile_dir()   # $1 is the dir to get it
{
  echo "Preparing $1..."
    cd "$1"
    mvn clean package -DskipTests
    cd ..
}

compile_dir "update-service"
compile_dir "TerminalTransactionVerificationService"
compile_dir "WebTransactionService"

echo "Starting Docker containers..."
docker-compose up --build -d --scale terminal-transaction-docker-service="$1" --scale web-transaction-docker-service="$2"
echo "waiting the database run"
sleep 5
# Configure PostgreSQL and restart containers
echo "Setting up PostgreSQL..."
# if script is run with parameter "non-interactive"
DB_EXISTS=$(docker exec postgres-master psql -U postgres -tAc "SELECT reading_user FROM clientinfo_db WHERE PASSWORD='reading_pass'")
if [ "$DB_EXISTS" = "reading_user" ]; then
    echo "Database already exists"
else
    if [ "$3" = "--non-interactive" ]; then
      docker exec postgres-master psql -U postgres -c "CREATE DATABASE clientinfo_db;"
      docker exec postgres-master psql -U postgres -d clientinfo_db -c "CREATE USER reading_user WITH PASSWORD 'reading_pass';"
      docker exec postgres-master psql -U postgres -d clientinfo_db -c "GRANT CONNECT ON DATABASE clientinfo_db TO reading_user;"
      docker exec postgres-master psql -U postgres -d clientinfo_db -c "GRANT SELECT ON ALL TABLES IN SCHEMA public TO reading_user;"
      docker exec postgres-master psql -U postgres -d clientinfo_db -c "GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO reading_user;"
      docker exec postgres-master psql -U postgres -d clientinfo_db -c "GRANT USAGE ON SCHEMA public TO reading_user;"
      docker exec postgres-master psql -U postgres -d clientinfo_db -c "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO reading_user;"
    else
      docker exec -it postgres-master psql -U postgres -d clientinfo_db -c "CREATE USER reading_user WITH PASSWORD 'reading_pass';"
      docker exec -it postgres-master psql -U postgres -d clientinfo_db -c "GRANT CONNECT ON DATABASE clientinfo_db TO reading_user;"
      docker exec -it postgres-master psql -U postgres -d clientinfo_db -c "GRANT SELECT ON ALL TABLES IN SCHEMA public TO reading_user;"
      docker exec -it postgres-master psql -U postgres -d clientinfo_db -c "GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO reading_user;"
      docker exec -it postgres-master psql -U postgres -d clientinfo_db -c "GRANT USAGE ON SCHEMA public TO reading_user;"
      docker exec -it postgres-master psql -U postgres -d clientinfo_db -c "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO reading_user;"
    fi
fi