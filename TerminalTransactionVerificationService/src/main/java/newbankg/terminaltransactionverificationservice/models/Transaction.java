package newbankg.terminaltransactionverificationservice.models;

/**
 * Some fields that could be added:
 */
public record Transaction(String id, String cardNumber, String timestamp, int amountOfTransaction) {
}
