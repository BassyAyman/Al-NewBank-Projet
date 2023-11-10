package fr.igorbanque.updateservice.models;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;

import java.io.Serializable;

@Data
@Getter
@Setter
@Builder
@JsonDeserialize(using = TransactionDeserializer.class)
public class Transaction implements Serializable {

    private String clientFirstName;
    private String clientLastName;
    private int amountOfTransaction;
    private String clientCreditCartDateExpiration;
    private String clientCVV;

    public Transaction(){}

    public Transaction(String clientFirstName, String clientLastName, int amountOfTransaction, String clientCreditCartDateExpiration, String clientCVV) {
        this.clientFirstName = clientFirstName;
        this.clientLastName = clientLastName;
        this.amountOfTransaction = amountOfTransaction;
        this.clientCreditCartDateExpiration = clientCreditCartDateExpiration;
        this.clientCVV = clientCVV;
    }
}
