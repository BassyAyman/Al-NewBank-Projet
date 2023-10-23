package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusness.IAccountInformation;
import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.Transaction;
import org.springframework.stereotype.Component;

@Component
public class AccountInformation implements IAccountInformation {

    @Override
    public Account getAccountFromCardId(Transaction transaction) {
        return new Account();
    }

}
