#!/bin/bash
# used to save the current state of the database (the file generated is used to populate the database in the docker-compose.yml)

DB_USER="postgres"
DB_HOST="localhost"
DB_NAME="clientinfo_db"

export PGPASSWORD="$DB_PASSWORD" # avoid password prompt

# if pg_dump is not installed, install it
if ! command -v pg_dump &> /dev/null
then
    echo "pg_dump could not be found, installing it..."
    # if ubuntu
    if [ -f /etc/lsb-release ]; then
        sudo apt-get install -y postgresql-client
    # if arch
    elif [ -f /etc/arch-release ]; then
        sudo pacman -S postgresql
    fi
fi
pg_dump -U "$DB_USER" -h "$DB_HOST" -d "$DB_NAME" > sql/test_data.sql

unset PGPASSWORD # remove password from environment

if [ $? -eq 0 ]; then
    echo "Database dumped successfully in test_data.sql"
else
    echo "Failed to dump database."
fi