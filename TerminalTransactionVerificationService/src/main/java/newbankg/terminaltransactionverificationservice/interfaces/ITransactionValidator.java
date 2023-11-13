package newbankg.terminaltransactionverificationservice.interfaces;

public interface ITransactionValidator {

    boolean makeTransactionWithCardId(long cardId, int amountOfTransaction);

}
