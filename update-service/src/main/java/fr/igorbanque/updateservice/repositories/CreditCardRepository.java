package fr.igorbanque.updateservice.repositories;

import fr.igorbanque.updateservice.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

    CreditCard findCreditCardByCreditCardNumber(String creditCardNumber);

}
