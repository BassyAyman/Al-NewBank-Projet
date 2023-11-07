package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountInformation;
import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountValidator;
import newbankg.webtransactionservice.interfaces.accountbusiness.IBalanceChecker;
import newbankg.webtransactionservice.interfaces.accountbusiness.ILimitChecker;
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
        return limitChecker.checkLimit(transaction.getAmountOfTransaction(), customerAccount.getAccountLimit()) &&
                // Check balance
                balanceChecker.isBalanceOk(transaction.getAmountOfTransaction(), customerAccount.getAmountMoney());
    }
}
