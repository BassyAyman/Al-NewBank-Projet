package fr.igorbanque.retrieverservice.repositories;

import fr.igorbanque.retrieverservice.modeles.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findByCreditCartNumber(String creditCartNumber);
}
