package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.AlgoCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.BINCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CreditCardValidator implements ValidateCardValidation {

    @Autowired
    BINCheck binCheck;
    @Autowired
    AlgoCheck algoCheck;
    @Override
    public boolean validateCartInTransactionContext(Transaction transaction) {
        if (binCheck.checkCreditCardNumberCoherence(transaction.getClientCreditCartNumber())) {
            return true;
        } else if (algoCheck.validateCreditCardAlgoLuhn(transaction.getClientCreditCartNumber())) {
            return true;
        }
        return false;
    }
}
