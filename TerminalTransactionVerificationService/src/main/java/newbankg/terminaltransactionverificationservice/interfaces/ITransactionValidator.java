package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.terminaltransactionverificationservice.models.Account;

public interface ITransactionValidator {

    boolean makeTransaction(Account account, int amountOfTransaction);

}
