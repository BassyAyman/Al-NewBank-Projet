package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.IBalanceChecker;
import newbankg.terminaltransactionverificationservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Override
    public boolean isBalanceOk(Account clientAcc) {
        return true;
    }

}
