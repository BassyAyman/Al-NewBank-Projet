package newbankg.webtransactionservice.interfaces;

import newbankg.webtransactionservice.models.Account;

public interface IBalanceChecker {

    boolean isBalanceOk(Account clientAcc);

}
