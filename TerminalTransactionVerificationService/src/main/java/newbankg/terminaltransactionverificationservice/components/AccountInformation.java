package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.IAccountInformation;
import newbankg.terminaltransactionverificationservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountInformation implements IAccountInformation {

    @Override
    public Account getAccountFromCardId(long cardId) {
        return new Account(); // TODO
    }

}
