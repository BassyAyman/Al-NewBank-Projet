package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.ILimitChecker;
import org.springframework.stereotype.Component;

@Component
public class AccountRestrictions implements ILimitChecker {

    @Override
    public boolean checkLimit(int amountToPay, int balanceLimit) {
        return amountToPay <= balanceLimit;
    }

}
