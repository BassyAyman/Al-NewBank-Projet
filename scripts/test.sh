#!/bin/bash

set -f

WEB="http://localhost:80/payOnline?origin=FR"


test_post() {
  URL=$1
  DATA=$2
  EXPECTED_RESPONSE=$3

  RESPONSE=$(curl -s -X POST -H "Content-Type: application/json" -d "$DATA" "$URL")

  if [[ "$RESPONSE" != "$EXPECTED_RESPONSE" ]]; then
    echo "Error: $URL != '$EXPECTED_RESPONSE'. Content $RESPONSE"
    echo "$URL unsuccessful"
    exit 1
  fi

  echo "$URL successful"
}

echo "--------------------"
echo "Valid transaction"
JSON_DATA='{
    "clientFirstName": "John",
    "clientLastName": "Doe",
    "amountOfTransaction": 10,
    "clientCreditCardNumber": "4532759734545858",
    "clientCreditCartDateExpiration": "12/24",
    "clientCVV": "123"
}'
test_post "$WEB" "$JSON_DATA" "Transaction successful"

echo "--------------------"
echo "Wrong luhn algo"
JSON_DATA='{
    "clientFirstName": "John",
    "clientLastName": "Doe",
    "amountOfTransaction": 10,
    "clientCreditCardNumber": "4532759734545850",
    "clientCreditCartDateExpiration": "12/24",
    "clientCVV": "123"
}'
test_post "$WEB" "$JSON_DATA" "Card is not valid"

echo "--------------------"
echo "Card expired"
JSON_DATA='{
    "clientFirstName": "John",
    "clientLastName": "Doe",
    "amountOfTransaction": 10,
    "clientCreditCardNumber": "453275973454585",
    "clientCreditCartDateExpiration": "10/23",
    "clientCVV": "123"
}'
test_post "$WEB" "$JSON_DATA" "Card is not valid"


echo "--------------------"
echo "Too big transaction"
JSON_DATA='{
    "clientFirstName": "John",
    "clientLastName": "Doe",
    "amountOfTransaction": 100000000,
    "clientCreditCardNumber": "453275973454585",
    "clientCreditCartDateExpiration": "10/23",
    "clientCVV": "123"
}'
test_post "$WEB" "$JSON_DATA" "Card is not valid"

echo "--------------------"
echo "Web transaction valid"
JSON_DATA='{
    "clientFirstName": "John",
    "clientLastName": "Doe",
    "amountOfTransaction": 10,
    "clientCreditCardNumber": "4532759734545858",
    "clientCreditCartDateExpiration": "12/24",
    "clientCVV": "123"
}'
test_post "$WEB" "$JSON_DATA" "Transaction successful"

## if needed
test_get() {
  RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" "$1")

  if [[ "$RESPONSE" != "200" ]]; then
    echo "Error: $1 content: $RESPONSE"
    echo "$URL unsuccessful"
    exit 1
  fi

  CONTENT=$(curl -s "$1")
  if [[ "$CONTENT" != "$2" ]]; then
    echo "Error: $1 != '$2' content: $CONTENT"
    echo "$URL unsuccessful"
    exit 1
  fi

  echo "$1 successful"
}

test_put() {
  RESPONSE=$(curl -s -o /dev/null -w "%{http_code}" -X PUT "$1")

  if [[ "$RESPONSE" != "200" ]]; then
    echo "Error: $1 content: $RESPONSE"
    echo "$URL unsuccessful"
    exit 1
  fi

  echo "$1 successful"
}