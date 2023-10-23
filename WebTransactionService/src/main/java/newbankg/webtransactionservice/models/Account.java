package newbankg.webtransactionservice.models;


import lombok.Data;

@Data
public class Account {
    private Client clientAccount;

    /**
     * Current Sold in the client account
     */
    private int amountMoney;

    /**
     * The amount of money after a transaction, that have to be debited to
     * the client after the final transaction has been made in the banking network
     */
    private int inDebitAmount;
    private int accountLimit;

}
