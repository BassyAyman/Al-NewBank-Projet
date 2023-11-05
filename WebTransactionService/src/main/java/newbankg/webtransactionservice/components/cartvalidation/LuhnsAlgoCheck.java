package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.AlgoCheck;
import org.springframework.stereotype.Component;

@Component
public class LuhnsAlgoCheck implements AlgoCheck {
    @Override
    public boolean validateCreditCardAlgoLuhn(String CCNumber) {
        CCNumber = new StringBuilder(CCNumber).reverse().toString();

        int sum = 0;
        boolean doubleDigit = false;

        for (int i = 0; i < CCNumber.length(); i++) {
            int digit = Character.getNumericValue(CCNumber.charAt(i));

            if (doubleDigit) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }

            sum += digit;
            doubleDigit = !doubleDigit;
        }

        return sum % 10 == 0;
    }
}
