package newbankg.webtransactionservice.interfaces.accountbusiness;

import newbankg.webtransactionservice.models.Account;

public interface IBalanceChecker {

    int isBalanceOk(int amountToPay, Account client);

}
