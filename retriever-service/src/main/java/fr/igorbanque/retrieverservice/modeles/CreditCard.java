package fr.igorbanque.retrieverservice.modeles;

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
    @Column(name = "credit_cart_number")
    private String creditCartNumber;
    @Column(name = "credit_cart_date_expiration")
    private String creditCartDateExpiration;
    @Column(name = "cvv")
    private String cvv;

    public CreditCard(){}

}
