package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.IAccountValidator;
import newbankg.terminaltransactionverificationservice.interfaces.IBalanceChecker;
import newbankg.terminaltransactionverificationservice.interfaces.ILimitChecker;
import newbankg.terminaltransactionverificationservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator implements IAccountValidator {

    @Autowired
    ILimitChecker limitChecker;

    @Autowired
    IBalanceChecker balanceChecker;


    @Override
    public boolean checkAccount(Account customer, int amountOfTransaction) {
        // Check spend limit
        return limitChecker.checkLimit(amountOfTransaction, customer.getAccountLimit()) &&
                // Check balance
                balanceChecker.isBalanceOk(amountOfTransaction, customer);
    }
}
