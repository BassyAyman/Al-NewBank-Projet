package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusness.ILimitChecker;
import org.springframework.stereotype.Component;

@Component
public class AccountRestrictions implements ILimitChecker {

    @Override
    public boolean checkLimit(int ammountToPay, int balanceLimit) {
        return ammountToPay <= balanceLimit;
    }

}
