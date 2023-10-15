package newbankg.terminaltransactionverificationservice.components;

import newbankg.webtransactionservice.interfaces.IAccountInformation;
import newbankg.webtransactionservice.interfaces.IAccountValidator;
import newbankg.webtransactionservice.interfaces.IBalanceChecker;
import newbankg.webtransactionservice.interfaces.ILimitChecker;
import newbankg.webtransactionservice.models.Account;
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
    public boolean checkAccountWithId(long id) {
        // Searching for account data
        Account customer = accountInformation.getAccountFromCardId(id);
        // Check spend limit
        return limitChecker.checkLimit(id) &&
                // Check balance
                balanceChecker.isBalanceOk(customer);
    }
}
