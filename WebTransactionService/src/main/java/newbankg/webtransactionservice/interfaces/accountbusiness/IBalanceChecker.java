package newbankg.webtransactionservice.interfaces.accountbusiness;

import newbankg.webtransactionservice.models.Account;

public interface IBalanceChecker {

    boolean isBalanceOk(int amountToPay, Account client);

}
