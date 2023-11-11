package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.AlgoCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.BINCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

@Component
public class CreditCardValidator implements ValidateCardValidation {

    private static final Logger LOGGER = Logger.getLogger(CreditCardValidator.class.getName());

    @Autowired
    BINCheck binCheck;
    @Autowired
    AlgoCheck algoCheck;

    @Override
    public boolean validateCardInTransactionContext(CreditCard cb) {
        String creditCardNumber = cb.getCreditCardNumber();
        LOGGER.info("Validating credit card number: " + creditCardNumber);

        boolean isExpired = isCardExpired(cb.getCreditCartDateExpiration());
        LOGGER.info("Card expiration status: " + (isExpired ? "Expired" : "Valid"));

        boolean isNumberCoherent = binCheck.checkCreditCardNumberCoherence(creditCardNumber);
        LOGGER.info("BIN number coherence status: " + (isNumberCoherent ? "Coherent" : "Incoherent"));

        boolean isValidAlgo = algoCheck.validateCreditCardAlgoLuhn(creditCardNumber);
        LOGGER.info("Luhn algorithm validation status: " + (isValidAlgo ? "Passed" : "Failed"));

        return !isExpired && isNumberCoherent && isValidAlgo;
    }

    @Override
    public boolean isCardExpired(String expirationDate) {
        LocalDate expiry = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("MM/yy"));
        boolean isExpired = expiry.isBefore(LocalDate.now());
        LOGGER.info("Checking card expiration. Expiration Date: " + expirationDate + ", Is expired: " + isExpired);
        return isExpired;
    }
}
