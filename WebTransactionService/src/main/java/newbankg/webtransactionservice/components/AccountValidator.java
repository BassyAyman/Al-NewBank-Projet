package newbankg.webtransactionservice.components;

import newbankg.webtransactionservice.interfaces.IAccountValidator;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator implements IAccountValidator {


    @Override
    public boolean checkAccountWithId(long id) {
        // Searching for account data

        // Check spend limit

        // Check balance

        return true;
    }
}
