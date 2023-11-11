package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.AlgoCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.BINCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.CreditCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CreditCardValidator implements ValidateCardValidation {

    private static final Logger LOGGER = LoggerFactory.getLogger(CreditCardValidator.class);

    @Autowired
    BINCheck binCheck;
    @Autowired
    AlgoCheck algoCheck;

    @Override
    public boolean validateCardInTransactionContext(CreditCard cb) {
        String creditCardNumber = cb.getCreditCardNumber();
        boolean isExpired = isCardExpired(cb.getCreditCartDateExpiration());
        boolean isNumberCoherent = binCheck.checkCreditCardNumberCoherence(creditCardNumber);
        boolean isValidAlgo = algoCheck.validateCreditCardAlgoLuhn(creditCardNumber);

        LOGGER.debug("Validating credit card number: {}", creditCardNumber);
        LOGGER.debug("Is card expired: {}", isExpired);
        LOGGER.debug("Is BIN number coherent: {}", isNumberCoherent);
        LOGGER.debug("Does card number pass Luhn algorithm check: {}", isValidAlgo);

        return !isExpired && isNumberCoherent && isValidAlgo;
    }

    @Override
    public boolean isCardExpired(String expirationDate) {
        LocalDate expiry = LocalDate.parse(expirationDate, DateTimeFormatter.ofPattern("MM/yy"));
        boolean isExpired = expiry.isBefore(LocalDate.now());
        LOGGER.debug("Checking if card is expired. Expiration Date: {}, Is Expired: {}", expirationDate, isExpired);
        return isExpired;
    }
}
