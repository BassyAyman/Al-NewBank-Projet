package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.IBalanceChecker;
import newbankg.terminaltransactionverificationservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Override
    public boolean isBalanceOk(int amountToPay, Account client) {
        return amountToPay <= client.getAmountMoney() + client.getInDebitAmount();
    }

}
