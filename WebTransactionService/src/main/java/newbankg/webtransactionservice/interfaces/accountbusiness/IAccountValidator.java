package newbankg.webtransactionservice.interfaces.accountbusiness;

import newbankg.webtransactionservice.models.Transaction;

public interface IAccountValidator {

    boolean checkAccountWithId(Transaction transaction);

}
