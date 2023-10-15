package newbankg.webtransactionservice.components;

import newbankg.webtransactionservice.interfaces.IAccountValidator;
import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TransactionValidationManager implements ITransactionValidator {

    private static final Logger LOGGER = Logger.getLogger(TransactionValidationManager.class.getSimpleName());

    @Autowired
    IAccountValidator accountValidator;

    @Override
    public boolean makeTransactionWithCardId(long cardId) {
        LOGGER.log(Level.INFO, "Proceeding to account validation");
        accountValidator.checkAccountWithId(cardId);
        return false;
    }
}
