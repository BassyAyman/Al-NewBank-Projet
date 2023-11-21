#!/bin/bash

# Generates credit cards and outputs to a CSV file
# Usage: ./generate-credit-cards.sh <number-of-credit-cards> [<percentage-of-okay-cards>]

set -e

# Define card prefixes
generate_visa_prefix() { echo "4$(($RANDOM % 10))"; }
generate_mastercard_prefix() {
    local prefixes=(51 52 53 54 55 2221 2222 2223 2224 2225 2226 2227 2228 2229 223 224 225 226 227 228 229 23 24 25 26 27)
    echo "${prefixes[$(($RANDOM % ${#prefixes[@]}))]}"
}
generate_amex_prefix() { echo $((RANDOM % 2 == 0 ? 34 : 37)); }
generate_random_prefix() {
    local prefixes=( $(generate_visa_prefix) $(generate_mastercard_prefix) $(generate_amex_prefix) )
    echo "${prefixes[$(($RANDOM % ${#prefixes[@]}))]}"
}

# Luhn algorithm to calculate the checksum
calculate_luhn_checksum() {
    local num=$1 sum=0 alt=0
    for (( i=${#num}-1; i>=0; i-- )); do
        local digit=${num:i:1}
        ((alt ^= 1)) && ((digit *= 2)) && ((digit -= digit > 9 ? 9 : 0))
        ((sum += digit))
    done
    echo $(( (10 - sum % 10) % 10 ))
}

# Generates a credit card number
generate_card_number() {
    local prefix;
    prefix=$(generate_random_prefix) length=$1
    local card_number="${prefix}"

    for (( i=${#prefix}; i<length-1; i++ )); do
        card_number+=$((RANDOM % 10))
    done
    card_number+=$(calculate_luhn_checksum "$card_number")

    echo "$card_number"
}

# Check if the given year is current or future
is_current_or_future_year() {
    (( $1 >= $(date +"%Y") ))
}

# Generate a valid credit card
generate_valid_card() {
    local length=16 # Standard length for valid cards
    local card_number;
    card_number=$(generate_card_number $length)
    local cvv=$((RANDOM % 900 + 100)) # 3-digit CVV
    local expiration_month;
    expiration_month=$(printf "%02d" $((RANDOM % 12 + 1)))
    local expiration_year=$((RANDOM % 5 + $(date +"%Y"))) # Within next 5 years

    echo "$card_number,$cvv,$expiration_month/$expiration_year"
}

# Main script logic
if [ $# -lt 1 ] || [ $# -gt 2 ]; then
    echo "Usage: $0 <number-of-credit-cards> [<percentage-of-okay-cards>]"
    exit 1
fi

num_cards=$1
percent_okay=${2:-100} # Default to 100% okay cards

echo "Generating $num_cards credit cards with $percent_okay% valid... into csv from bash"

# Remove old file if exists
[ -f credit-cards.csv ] && rm credit-cards.csv

# Generate cards
for (( i=0; i<num_cards; i++ )); do
    if [ $((RANDOM % 100)) -lt "$percent_okay" ]; then
        card_details=$(generate_valid_card)
    else
        card_length=$((RANDOM % 7 + 13)) # Random card length between 13 and 19
        card_number=$(generate_card_number $card_length)
        cvv=$((RANDOM % 1000))
        expiration_month=$(printf "%02d" $((RANDOM % 12 + 1)))
        expiration_year=$((RANDOM % 21 + 2000)) # Random year between 2000 and 2020
        card_details="$card_number,$cvv,$expiration_month/$expiration_year"
    fi
    echo "$card_details" >> credit-cards.csv
done
