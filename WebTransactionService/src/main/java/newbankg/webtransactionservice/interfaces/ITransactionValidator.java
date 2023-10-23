package newbankg.webtransactionservice.interfaces;

import newbankg.webtransactionservice.models.Transaction;

public interface ITransactionValidator {

    boolean makeTransactionWithCardId(Transaction transaction);

}
