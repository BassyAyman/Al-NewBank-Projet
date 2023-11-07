package newbankg.webtransactionservice.components;

import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountValidator;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TransactionValidationManager implements ITransactionValidator {

    private static final Logger LOGGER = Logger.getLogger(TransactionValidationManager.class.getSimpleName());

    @Autowired
    IAccountValidator accountValidator;
    @Autowired
    ValidateCardValidation validateCardValidation;

    @Override
    public boolean makeTransactionWithCardId(Transaction transaction) {
        LOGGER.log(Level.INFO, "Proceeding to account validation");
        boolean isCardValid = validateCardValidation.validateCartInTransactionContext(transaction);
        boolean isAccountValid = accountValidator.checkAccountWithId(transaction);
        return isCardValid && isAccountValid;
    }

}
