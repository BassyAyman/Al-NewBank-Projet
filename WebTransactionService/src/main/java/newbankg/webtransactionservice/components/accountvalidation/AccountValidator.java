package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusness.IAccountInformation;
import newbankg.webtransactionservice.interfaces.accountbusness.IAccountValidator;
import newbankg.webtransactionservice.interfaces.accountbusness.IBalanceChecker;
import newbankg.webtransactionservice.interfaces.accountbusness.ILimitChecker;
import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator implements IAccountValidator {

    @Autowired
    IAccountInformation accountInformation;

    @Autowired
    ILimitChecker limitChecker;

    @Autowired
    IBalanceChecker balanceChecker;


    @Override
    public boolean checkAccountWithId(Transaction transaction) {
        // Searching for account data
        Account customerAccount = accountInformation.getAccountFromCardId(transaction);
        // Check spend limit
        return limitChecker.checkLimit(transaction.getAmoutOfTransaction(), customerAccount.getAccountLimit()) &&
                // Check balance
                balanceChecker.isBalanceOk(transaction.getAmoutOfTransaction(), customerAccount.getAmountMoney());
    }
}
