package newbankg.terminaltransactionverificationservice.components;

import newbankg.    webtransactionservice.interfaces.IBalanceChecker;
import newbankg.webtransactionservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Override
    public boolean isBalanceOk(Account clientAcc) {
        return true;
    }

}
