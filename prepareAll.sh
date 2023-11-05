#!/bin/bash

set -e

function compile_dir()   # $1 is the dir to get it
{
  echo "Preparing $1..."
    cd $1
    mvn clean package
    cd ..
}

compile_dir "retriever-service"
compile_dir "update-service"

echo "Starting Docker containers..."
docker-compose up --build -d
echo "waiting the database run"
sleep 5
# Configure PostgreSQL and restart containers
echo "Setting up PostgreSQL..."
docker exec -it clientinfo-database psql -U postgres -d clientinfo_db -c "CREATE USER reading_user WITH PASSWORD 'reading_pass';"
docker exec -it clientinfo-database psql -U postgres -d clientinfo_db -c "GRANT CONNECT ON DATABASE clientinfo_db TO reading_user;"
docker exec -it clientinfo-database psql -U postgres -d clientinfo_db -c "GRANT SELECT ON ALL TABLES IN SCHEMA public TO reading_user;"
docker exec -it clientinfo-database psql -U postgres -d clientinfo_db -c "GRANT SELECT ON ALL SEQUENCES IN SCHEMA public TO reading_user;"
docker exec -it clientinfo-database psql -U postgres -d clientinfo_db -c "GRANT USAGE ON SCHEMA public TO reading_user;"
docker exec -it clientinfo-database psql -U postgres -d clientinfo_db -c "ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT SELECT ON TABLES TO reading_user;"