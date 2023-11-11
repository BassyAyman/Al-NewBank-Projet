package newbankg.webtransactionservice.models;

public record Transaction(
        String clientFirstName,
        String clientLastName,
        int amountOfTransaction,
        String clientCreditCardNumber,
        String clientCreditCartDateExpiration,
        String clientCVV) { }


