package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.IBalanceChecker;
import newbankg.webtransactionservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Override
    public boolean isBalanceOk(int amountToPay, Account client) {
        return amountToPay <= client.getAmountMoney() + client.getInDebitAmount();
    }

}
