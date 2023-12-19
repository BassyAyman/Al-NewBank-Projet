package newbankg.webtransactionservice.interfaces;

import newbankg.webtransactionservice.models.Transaction;

public interface ITransactionValidator {

    Transaction makeTransactionWithCardId(Transaction transaction);

    Transaction makeTransactionWithGateway(Transaction transaction);

}
