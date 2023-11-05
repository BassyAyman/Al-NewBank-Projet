package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.IBalanceChecker;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Override
    public boolean isBalanceOk(int amountToPay, int clientBalance) {
        return amountToPay <= clientBalance;
    }

}
