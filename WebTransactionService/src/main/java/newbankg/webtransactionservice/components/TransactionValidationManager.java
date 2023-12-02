package newbankg.webtransactionservice.components;

import newbankg.webtransactionservice.InvalidTransactionException;
import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountValidator;
import newbankg.webtransactionservice.interfaces.cartbusiness.ValidateCardValidation;
import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.CreditCard;
import newbankg.webtransactionservice.models.Transaction;
import newbankg.webtransactionservice.redisrepo.RedisFunction;
import newbankg.webtransactionservice.repositories.AccountRepository;
import newbankg.webtransactionservice.repositories.CreditCardRepository;
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

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;
    @Autowired
    RedisFunction redisFunction;

    /**
     * Check if the transaction is valid, if not throw an exception.
     * Account is found by the card number. and the card is found by the transaction.
     * <p>
     * Transaction is valid if:
     * - the card is valid:
     * - the card is not expired
     * - the card number is coherent
     * - the card number is valid according to the Luhn algorithm
     * - the account is valid:
     * - the account has enough money
     * - the account has enough limit
     * - the account is not blocked, expired...closed.. maybe???
     *
     * @param transaction Transaction to check
     * @throws InvalidTransactionException if the transaction is not valid
     */
    @Override
    public Transaction makeTransactionWithCardId(Transaction transaction) throws InvalidTransactionException {
        LOGGER.log(Level.INFO, "Proceeding to account validation");

        CreditCard creditCard = creditCardRepository.findByCreditCardNumber(transaction.clientCreditCardNumber());
        if (null == creditCard || !validateCardValidation.validateCardInTransactionContext(creditCard)) {
            LOGGER.log(Level.INFO, "Card is not valid");
            throw new InvalidTransactionException("Card is not valid");
        }

        Account account = accountRepository.findById(creditCard.getClientInformation().getCustomerIdentifier());
        if (null == account || !accountValidator.checkAccount(account, transaction.amountOfTransaction())) {
            LOGGER.log(Level.INFO, "Account is not valid");
            throw new InvalidTransactionException("Account is not valid");
        }
        // line in manner of test //TODO remove if logique ok
        int debit = redisFunction.getClientDebitInContext(account.getClientAccount().getCustomerIdentifier()).get();
        LOGGER.log(Level.INFO, "Transaction is valid and new debit equal = "+ debit);
        // end of line
        return transaction;
    }
}
