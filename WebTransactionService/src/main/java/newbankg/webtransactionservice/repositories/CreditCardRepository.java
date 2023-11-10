package newbankg.webtransactionservice.repositories;

import newbankg.webtransactionservice.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findByCreditCardNumber(String creditCartNumber);
}
