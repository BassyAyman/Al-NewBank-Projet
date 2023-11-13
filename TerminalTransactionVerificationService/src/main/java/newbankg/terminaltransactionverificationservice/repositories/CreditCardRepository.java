package newbankg.terminaltransactionverificationservice.repositories;

import newbankg.terminaltransactionverificationservice.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findByCreditCardNumber(String creditCartNumber);

    CreditCard findCreditCardById(Long id);
}
