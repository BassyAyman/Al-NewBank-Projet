package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountValidator;
import newbankg.webtransactionservice.interfaces.accountbusiness.IBalanceChecker;
import newbankg.webtransactionservice.interfaces.accountbusiness.ILimitChecker;
import newbankg.webtransactionservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator implements IAccountValidator {

    @Autowired
    ILimitChecker limitChecker;

    @Autowired
    IBalanceChecker balanceChecker;

    @Autowired
    RedisUpdater redisUpdater;


    @Override
    public boolean checkAccount(Account account, int amountOfTransaction) {

        boolean limiok = limitChecker.checkLimit(amountOfTransaction, account.getAccountLimit());
        int newDebit = balanceChecker.isBalanceOk(amountOfTransaction, account);

        if(limiok && newDebit != -1) {
            // here we know that the final transaction is valid, so we can directly update the redis DB,
            // so we update in redis the new debit as the old debit + the amount of transaction
            redisUpdater.setNewDebitValueForClient(account.getClientAccount().getCustomerIdentifier(),
                    newDebit + amountOfTransaction);
            return true;
        }
        return false;
    }
}
