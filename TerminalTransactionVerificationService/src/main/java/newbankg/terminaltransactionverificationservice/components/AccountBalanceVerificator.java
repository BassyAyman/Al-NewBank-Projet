package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.IBalanceChecker;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Override
    public boolean isBalanceOk(int amountToPay, int clientBalance) {
        return amountToPay <= clientBalance;
    }

}
