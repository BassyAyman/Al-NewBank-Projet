package newbankg.terminaltransactionverificationservice.components;

import newbankg.webtransactionservice.interfaces.IAccountInformation;
import newbankg.webtransactionservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountInformation implements IAccountInformation {

    @Override
    public Account getAccountFromCardId(long cardId) {
        return new Account("Test", "ImOk", 12345678);
    }

}
