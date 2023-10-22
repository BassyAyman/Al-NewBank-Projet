package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.terminaltransactionverificationservice.models.Account;

public interface IBalanceChecker {

    boolean isBalanceOk(Account clientAcc);

}
