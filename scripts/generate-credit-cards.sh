#!/bin/bash

# generate credit cards on csv file
# usage: ./generate-credit-cards.sh <number-of-credit-cards>

# for the advanced logic to make valid and unvalid credit cards
# usage: ./generate-credit-cards.sh <number-of-credit-cards> <percentage-of-okay-cards>

set -e

generate_visa_prefix() {
    # Visa cards start with a 4.
    echo "4$(($RANDOM % 10))"
}

generate_mastercard_prefix() {
    # MasterCard start with numbers from 51 to 55 or 2221 to 2720.
    local prefixes=(51 52 53 54 55 2221 2222 2223 2224 2225 2226 2227 2228 2229 223 224 225 226 227 228 229 23 24 25 26 27)
    local idx=$(($RANDOM % ${#prefixes[@]}))
    echo "${prefixes[$idx]}"
}

generate_amex_prefix() {
    # AmEx cards start with 34 or 37.
    local prefixes=(34 37)
    local idx=$(($RANDOM % 2))
    echo "${prefixes[$idx]}"
}

generate_random_prefix() {
    local prefixes=( $(generate_visa_prefix) $(generate_mastercard_prefix) $(generate_amex_prefix) )
    local idx=$(($RANDOM % ${#prefixes[@]}))
    echo "${prefixes[$idx]}"
}

generate_card_number() {
    local PREFIX=$(generate_random_prefix)
    local LENGTH=$1
    local NUM_DIGITS=$((LENGTH - ${#PREFIX}))

    for ((i=0; i<NUM_DIGITS; i++)); do
        local DIGIT=$((RANDOM % 10))
        PREFIX="${PREFIX}${DIGIT}"
    done

    local CHECKSUM=$(calculate_luhn_checksum "$PREFIX")
    echo "${PREFIX}${CHECKSUM}"
}

calculate_luhn_checksum() {
    NUM=$1
    SUM=0
    ALT=0
    LEN=${#NUM}

    for ((i=$LEN-1; i>=0; i--)); do
        DIGIT=${NUM:i:1}
        if [ $ALT -eq 1 ]; then
            DIGIT=$((DIGIT * 2))
            if [ $DIGIT -gt 9 ]; then
                DIGIT=$((DIGIT - 9))
            fi
        fi
        SUM=$((SUM + DIGIT))
        ALT=$((!ALT))
    done

    CHECKSUM=$((10 - SUM % 10))
    [ $CHECKSUM -eq 10 ] && CHECKSUM=0
    echo $CHECKSUM
}


if [ $# -lt 1 ]; then
    echo "Usage: $0 <number-of-credit-cards>"
    exit 1
fi

NUM_CARDS=$1

if [ -f credit-cards.csv ]; then
    echo "Removing old credit-cards.csv file..."
    rm credit-cards.csv
fi

echo "Generating $1 credit cards... into csv from bash"
for ((i=0; i<NUM_CARDS; i++)); do
    CARD_LENGTH=$((RANDOM % 7 + 13)) # Random card length between 13 and 19
    CARD_NUMBER=$(generate_card_number $CARD_LENGTH)
    CARD_CVV=$((RANDOM % 1000))
    CARD_EXPIRATION_MONTH=$(printf "%02d" $((RANDOM % 12 + 1)))
    CARD_EXPIRATION_YEAR=$((RANDOM % 10 + $(date +"%Y")))
    echo "$CARD_NUMBER,$CARD_CVV,$CARD_EXPIRATION_MONTH/$CARD_EXPIRATION_YEAR" >> credit-cards.csv
done

# advanced logic to make valid and unvalid credit cards
# usage: ./generate-credit-cards.sh <number-of-credit-cards> <percentage-of-okay-cards>

is_current_or_future_year() {
    local year;
    year=$1
    local current_year;
    current_year=$(date +"%Y")
    if [ "$year" -ge "$current_year" ]; then
        return 0
    else
        return 1
    fi
}

generate_okay_card() {
    local LENGTH=16  # Standard length for "okay" cards
    local PREFIX;
    PREFIX=$(generate_random_prefix)
    local NUM_DIGITS=$((LENGTH - ${#PREFIX} - 1))
    local CARD_NUMBER_PART=""

    for ((i=0; i<NUM_DIGITS; i++)); do
        CARD_NUMBER_PART="${CARD_NUMBER_PART}$((RANDOM % 10))"
    done

    local CARD_NUMBER="${PREFIX}${CARD_NUMBER_PART}"
    local CHECKSUM;
    CHECKSUM=$(calculate_luhn_checksum "$CARD_NUMBER")
    CARD_NUMBER="${CARD_NUMBER}${CHECKSUM}"

    local CARD_CVV=$((RANDOM % 900 + 100))  # CVV of 3 digits
    local CARD_EXPIRATION_MONTH;
    CARD_EXPIRATION_MONTH=$(printf "%02d" $((RANDOM % 12 + 1)))
    local CARD_EXPIRATION_YEAR=$((RANDOM % 5 + $(date +"%Y")))  # Within next 5 years

    echo "$CARD_NUMBER,$CARD_CVV,$CARD_EXPIRATION_MONTH/$CARD_EXPIRATION_YEAR"
}

if [ $# -gt 3 ]; then
    echo "Usage: $0 <number-of-credit-cards> <percentage-of-okay-cards>"
    exit 1
fi

NUM_CARDS=$1
PERCENT_OKAY=$2


echo "Generating $1 credit cards (with $2% okay cards)... into csv from bash"
for ((i=0; i<NUM_CARDS; i++)); do
    if [ $(($RANDOM % 100)) -lt $PERCENT_OKAY ]; then
        # Generate an okay card
        CARD_DETAILS=$(generate_okay_card)
    else
        # Generate a random card
        CARD_LENGTH=$((RANDOM % 7 + 13)) # Random card length between 13 and 19
        CARD_NUMBER=$(generate_card_number $CARD_LENGTH)
        CARD_CVV=$((RANDOM % 1000))
        CARD_EXPIRATION_MONTH=$(printf "%02d" $((RANDOM % 12 + 1)))
        CARD_EXPIRATION_YEAR=$((RANDOM % 21 + 2000)) # Random year between 2000 and 2020
        CARD_DETAILS="$CARD_NUMBER,$CARD_CVV,$CARD_EXPIRATION_MONTH/$CARD_EXPIRATION_YEAR"
    fi
    echo "$CARD_DETAILS" >> credit-cards.csv
done