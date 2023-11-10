package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.AlgoCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.BINCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CreditCardValidator implements ValidateCardValidation {

    @Autowired
    BINCheck binCheck;
    @Autowired
    AlgoCheck algoCheck;

    @Override
    public boolean validateCardInTransactionContext(CreditCard cb) {
        String creditCardNumber = cb.getCreditCardNumber();
        return !isCardExpired(cb.getCreditCartDateExpiration()) //
                && binCheck.checkCreditCardNumberCoherence(creditCardNumber) //
                && algoCheck.validateCreditCardAlgoLuhn(creditCardNumber);
    }


    @Override
    public boolean isCardExpired(String expirationDate) {
        LocalDate expiry = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("MM/yy"));
        return expiry.isBefore(LocalDate.now());
    }
}
