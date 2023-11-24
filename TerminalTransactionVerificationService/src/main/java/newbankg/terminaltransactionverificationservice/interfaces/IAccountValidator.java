package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.terminaltransactionverificationservice.models.Account;

public interface IAccountValidator {

    boolean checkAccount(Account account, int amountOfTransaction);

}
