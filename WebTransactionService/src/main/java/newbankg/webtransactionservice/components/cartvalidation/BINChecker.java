package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusness.BINCheck;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class BINChecker implements BINCheck {

    private final String RIGHT_REGEX_CCNUMBER_FORMAT = "^[0-9]{16}$";

    @Override
    public boolean checkCreditCardNumberCoherence(String CCNumber) {
        Pattern pattern = Pattern.compile(RIGHT_REGEX_CCNUMBER_FORMAT);
        return pattern.matcher((CCNumber)).matches();
    }
}
