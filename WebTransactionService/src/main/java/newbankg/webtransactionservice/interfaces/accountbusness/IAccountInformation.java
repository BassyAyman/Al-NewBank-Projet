package newbankg.webtransactionservice.interfaces.accountbusness;

import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.Transaction;

public interface IAccountInformation {

    Account getAccountFromCardId(Transaction transaction);

}
