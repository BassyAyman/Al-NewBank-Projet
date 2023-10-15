package newbankg.webtransactionservice.components;

import newbankg.webtransactionservice.interfaces.ILimitChecker;
import org.springframework.stereotype.Component;

@Component
public class AccountRestrictions implements ILimitChecker {

    @Override
    public boolean checkLimit(long id) {
        return true;
    }

}
