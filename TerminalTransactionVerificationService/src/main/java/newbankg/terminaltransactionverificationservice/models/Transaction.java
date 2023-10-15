package newbankg.terminaltransactionverificationservice.models;

/**
 * Some fields that could be added:
 * @param id
 * @param cardId
 * @param amount
 * @param currency
 * @param merchantId
 * @param terminalId
 * @param transactionDate
 * @param transactionTime
 * @param transactionType: 0 - payment, 1 - withdrawal, 2 - deposit, 3 - transfer, 4 - balance, 5 - pin change, 6 - card block, 7 - card unblock
 * @param transactionStatus 0 - pending, 1 - approved, 2 - declined
 * @param transactionResult 0 - success, 1 - fail
 */
public record Transaction(String id, long cardId, String timestamp) {
}
