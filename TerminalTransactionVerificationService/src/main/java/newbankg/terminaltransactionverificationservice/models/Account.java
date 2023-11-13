package newbankg.terminaltransactionverificationservice.models;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "account")
public class Account {
    @Id
    private Long id;
    @OneToOne
    private Client clientAccount;


    /**
     * Current Sold in the client account
     */
    @Column(name = "amount_money")
    private int amountMoney;

    /**
     * The amount of money after a transaction, that have to be debited to
     * the client after the final transaction has been made in the banking network
     */
    @Column(name = "in_debit_amount")
    private int inDebitAmount;
    @Column(name = "account_limit")
    private int accountLimit;

    public Account() {
    }

}