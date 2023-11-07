package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.BINCheck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class BINCheckerTest {

    @Autowired
    private BINCheck binCheck;

    @Test
    public void patternRegexOnGoodCCNumberValueTest(){
        String validCCNumber = "1234567890123456";
        boolean result = binCheck.checkCreditCardNumberCoherence(validCCNumber);
        assertTrue(result);
    }

    @Test
    public void patternRegexOnBadWithNoGoodLenCCNumberValueTest(){
        String invalidCCNumber = "12345678";
        boolean result = binCheck.checkCreditCardNumberCoherence(invalidCCNumber);
        assertFalse(result);
    }

    @Test
    public void patternRegexOnBadCCNumberValueTest(){
        String invalidCCNumber = "1234abcd567890123";
        boolean result = binCheck.checkCreditCardNumberCoherence(invalidCCNumber);
        assertFalse(result);
    }

    @Test
    public void patternRegexOnBadSpaceCCNumberValueTest(){
        String invalidCCNumber = "4532 0151 1282 0366";
        boolean result = binCheck.checkCreditCardNumberCoherence(invalidCCNumber);
        assertFalse(result);
    }

}