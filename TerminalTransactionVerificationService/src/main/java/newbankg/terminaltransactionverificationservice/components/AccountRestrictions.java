package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.ILimitChecker;
import org.springframework.stereotype.Component;

@Component
public class AccountRestrictions implements ILimitChecker {

    @Override
    public boolean checkLimit(int amountToPay, int balanceLimit) {
        return amountToPay <= balanceLimit;
    }

}
