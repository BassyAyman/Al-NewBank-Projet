package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusness.IBalanceChecker;
import newbankg.webtransactionservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Override
    public boolean isBalanceOk(int ammountToPay, int clientSolde) {
        return ammountToPay <= clientSolde;
    }

}
