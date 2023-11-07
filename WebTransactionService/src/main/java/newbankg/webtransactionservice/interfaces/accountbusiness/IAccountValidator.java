package newbankg.webtransactionservice.interfaces.accountbusiness;

import newbankg.webtransactionservice.models.Account;

public interface IAccountValidator {
    boolean checkAccount(Account account, int amountOfTransaction);
}
