package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.webtransactionservice.models.Account;

public interface ITerminalBalanceChecker {
    boolean isTerminalBalanceOk(Account clientAcc);

}
