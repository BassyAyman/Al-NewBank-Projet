#!/bin/bash

set -e

function compile_dir()   # $1 is the dir to get it
{
  echo "Preparing $1..."
    cd "$1"
    mvn clean package -DskipTests
    cd ..
}

compile_dir "WebTransactionService"

echo "Starting Docker containers..."
docker-compose up --build -d
echo "waiting the database run"
sleep 5
# Configure PostgreSQL and restart containers
echo "Setting up PostgreSQL..."
# if script is run with parameter "non-interactive"
AN_USER_ALREADY_EXISTS=$(docker exec postgres-master psql -U postgres -tAc "SELECT 1 FROM pg_roles WHERE rolname='reading_user'")
if [ "$AN_USER_ALREADY_EXISTS" = "1" ]; then
    echo "User reading_user already exists"
else
    if [ "$3" = "--non-interactive" ]; then
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