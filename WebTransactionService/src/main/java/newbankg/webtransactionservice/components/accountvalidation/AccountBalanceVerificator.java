package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.IBalanceChecker;
import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.redisrepo.RedisFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountBalanceVerificator implements IBalanceChecker {

    @Autowired
    private RedisFunction redisFunction;

    @Override
    public int isBalanceOk(int amountToPay, Account client) {
        int clientAccountDebit =
                redisFunction.getClientDebitInContext(client.getClientAccount().getCustomerIdentifier())
                        .orElseGet(() -> {
                            redisFunction.save(client.getClientAccount().getCustomerIdentifier(), amountToPay);
                            return amountToPay;
                        });
        return (amountToPay <= client.getAmountMoney() - clientAccountDebit)? clientAccountDebit : -1;
    }
}
