package newbankg.webtransactionservice.models;

import lombok.Data;

@Data
public class Transaction {

    private String clientFirstName;
    private String clientLastName;
    private int amoutOfTransaction;
    private String clientCreditCartNumber;
    private String clientCreditCartDateExpiration;
    private String clientCVV;

}
