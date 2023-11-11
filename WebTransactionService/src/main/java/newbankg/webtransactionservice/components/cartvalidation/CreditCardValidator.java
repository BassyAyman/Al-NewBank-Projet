package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.AlgoCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.BINCheck;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.CreditCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
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
        boolean isExpired = isCardExpired(cb.getCreditCartDateExpiration());
        boolean isNumberCoherent = binCheck.checkCreditCardNumberCoherence(creditCardNumber);
        boolean isValidAlgo = algoCheck.validateCreditCardAlgoLuhn(creditCardNumber);
        //Arrays.asList("Validating credit card number: " + creditCardNumber, "Is card expired: " + isExpired, "Is BIN number coherent: " + isNumberCoherent, "Does card number pass Luhn algorithm check: " + isValidAlgo)//
        //        .forEach(LOGGER::info);
        return !isExpired && isNumberCoherent && isValidAlgo;
    }

    @Override
    public boolean isCardExpired(String expirationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth expiry = YearMonth.parse(expirationDate, formatter);
        LocalDate lastDayOfMonth = expiry.atEndOfMonth();
        //LOGGER.info("Checking if card is expired. Expiration Date: " + lastDayOfMonth + " Today: " + LocalDate.now());
        return lastDayOfMonth.isBefore(LocalDate.now());
    }
}
