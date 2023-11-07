package newbankg.webtransactionservice.interfaces.cartbusiness;

import newbankg.webtransactionservice.models.Transaction;


public interface ValidateCardValidation {

    boolean validateCartInTransactionContext(Transaction transaction);

    boolean isCardExpired(String expirationDate);
}
