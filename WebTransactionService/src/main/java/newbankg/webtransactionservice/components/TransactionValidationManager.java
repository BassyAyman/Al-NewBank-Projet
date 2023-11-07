package newbankg.webtransactionservice.components;

import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountValidator;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.CreditCard;
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
        //TODO : recuperation de la carte depuis la DB et verifier que sa existe
        //TODO : recuperation Account avec client id .
        //TODO : Avec le compte et le montant de la transaction, faire les verifications
        boolean isAccountValid = accountValidator.checkAccountWithId(transaction);
        //TODO : une fois que tout est bon (validé), tu peux renvoyer OK et ta methode de verification doit renvoyer la transaction
        //TODO : Quand a un moment des verification sa passe pas, pas faire de false, mais une exception personnalisé qui est renvoyer
        return isCardValid && isAccountValid;
    }

}
