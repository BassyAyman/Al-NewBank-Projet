package newbank.gateway.greenlandgateway.repositories;

import newbank.gateway.greenlandgateway.models.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
    CreditCard findByCreditCardNumber(String creditCartNumber);
}
