package newbankg.webtransactionservice.interfaces.cartbusiness;

import newbankg.webtransactionservice.models.CreditCard;


public interface ValidateCardValidation {

    /**
     * Check if the card is valid.
     * If the card is expired
     * If the card number is not coherent
     * If the card number is not valid according to the Luhn algorithm
     *
     * @param creditCard CreditCard
     * @return boolean true if the card is valid
     */
    boolean validateCardInTransactionContext(CreditCard creditCard);

    /**
     * Check if the card is expired... according to the current date
     *
     * @param expirationDate String in the format MM/yy
     * @return boolean true if the card is expired
     */
    boolean isCardExpired(String expirationDate);
}
