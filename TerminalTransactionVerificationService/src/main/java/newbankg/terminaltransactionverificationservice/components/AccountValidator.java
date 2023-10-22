package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.IAccountInformation;
import newbankg.terminaltransactionverificationservice.interfaces.IAccountValidator;
import newbankg.terminaltransactionverificationservice.interfaces.IBalanceChecker;
import newbankg.terminaltransactionverificationservice.interfaces.ILimitChecker;
import newbankg.terminaltransactionverificationservice.models.Account;
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
