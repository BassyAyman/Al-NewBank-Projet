package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.terminaltransactionverificationservice.models.Account;

public interface IBalanceChecker {

    boolean isBalanceOk(int amountToPay, Account client);

}
