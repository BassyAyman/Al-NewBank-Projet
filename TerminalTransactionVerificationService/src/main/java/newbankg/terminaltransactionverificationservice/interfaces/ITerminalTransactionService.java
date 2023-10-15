package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.terminaltransactionverificationservice.models.Transaction;

public interface ITerminalTransactionService {
    boolean isTransactionValid(Transaction transaction);
}
