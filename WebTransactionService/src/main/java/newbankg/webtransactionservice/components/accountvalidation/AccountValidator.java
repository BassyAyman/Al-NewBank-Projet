package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountInformation;
import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountValidator;
import newbankg.webtransactionservice.interfaces.accountbusiness.IBalanceChecker;
import newbankg.webtransactionservice.interfaces.accountbusiness.ILimitChecker;
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
    public boolean checkAccount(Account account, int amountOfTransaction) {
        return limitChecker.checkLimit(amountOfTransaction, account.getAccountLimit())  //
                && balanceChecker.isBalanceOk(amountOfTransaction, account.getAmountMoney());
    }
}
