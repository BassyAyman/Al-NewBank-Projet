package newbank.gateway.greenlandgateway.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "credit_card")
public class CreditCard {

    @Id
    private Long id;
    @OneToOne
    private Client clientInformation;
    @Column(name = "credit_card_number")
    private String creditCardNumber;
    @Column(name = "credit_card_date_expiration")
    private String creditCartDateExpiration;
    @Column(name = "cvv")
    private String cvv;

    public CreditCard(){}
}
