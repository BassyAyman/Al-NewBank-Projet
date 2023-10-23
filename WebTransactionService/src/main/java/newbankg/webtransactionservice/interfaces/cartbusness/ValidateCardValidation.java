package newbankg.webtransactionservice.interfaces.cartbusness;

import newbankg.webtransactionservice.models.Transaction;


public interface ValidateCardValidation {

    boolean validateCartInTransactionContext(Transaction transaction);
}
