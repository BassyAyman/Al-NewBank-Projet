package newbankg.webtransactionservice.interfaces.accountbusness;

import newbankg.webtransactionservice.models.Account;

public interface IBalanceChecker {

    boolean isBalanceOk(int ammountToPay,int clientSolde);

}
