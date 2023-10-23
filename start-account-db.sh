# Launches locally an empty postgres account database

docker run --name account-db-container \
-e POSTGRES_USER=user \
-e POSTGRES_PASSWORD=password \
-e POSTGRES_DB=account-db \
-p 5432:5432 \
-e POSTGRES_HOST=localhost \
-d postgres:13