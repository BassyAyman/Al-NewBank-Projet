package newbankg.terminaltransactionverificationservice.repositories;

import newbankg.terminaltransactionverificationservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByCustomerIdentifier(long customerIdentifier);
}
