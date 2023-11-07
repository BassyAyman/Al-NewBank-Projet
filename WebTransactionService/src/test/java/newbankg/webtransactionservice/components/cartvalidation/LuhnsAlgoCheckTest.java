package newbankg.webtransactionservice.components.cartvalidation;

import newbankg.webtransactionservice.interfaces.cartbusiness.AlgoCheck;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LuhnsAlgoCheckTest {

    @Autowired
    AlgoCheck LuhnAlgorithm;

    @Test
    public void testValidCreditCardNumber() {
        assertTrue(LuhnAlgorithm.validateCreditCardAlgoLuhn("4012888888881881"));
        assertTrue(LuhnAlgorithm.validateCreditCardAlgoLuhn("6011000990139424"));
    }

    @Test
    public void testInvalidCreditCardNumber() {
        assertFalse(LuhnAlgorithm.validateCreditCardAlgoLuhn("4532015112820365"));
        assertFalse(LuhnAlgorithm.validateCreditCardAlgoLuhn("4012888888881882"));
        assertFalse(LuhnAlgorithm.validateCreditCardAlgoLuhn("6011000990139425"));
    }

    @Test
    public void testInvalidLengthCreditCardNumber() {
        assertFalse(LuhnAlgorithm.validateCreditCardAlgoLuhn("1234"));
        assertFalse(LuhnAlgorithm.validateCreditCardAlgoLuhn("12345678901234567890"));
    }

    @Test
    public void testInvalidCharactersCreditCardNumber() {
        assertFalse(LuhnAlgorithm.validateCreditCardAlgoLuhn("1234abcd567890123"));
    }

}