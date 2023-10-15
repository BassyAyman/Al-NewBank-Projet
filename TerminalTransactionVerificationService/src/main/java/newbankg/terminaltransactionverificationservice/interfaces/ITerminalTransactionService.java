package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.terminaltransactionverificationservice.models.Transaction;

public interface ITerminalTransactionService {
    void createTransaction(Transaction transaction);
    void validateTransaction(Transaction transaction);
}
