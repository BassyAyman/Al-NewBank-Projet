package newbankg.webtransactionservice.interfaces.accountbusness;

import newbankg.webtransactionservice.models.Transaction;

public interface IAccountValidator {

    boolean checkAccountWithId(Transaction transaction);

}
