package newbank.gateway.greenlandgateway.repositories;

import newbank.gateway.greenlandgateway.models.Account;
import newbank.gateway.greenlandgateway.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByClientAccount(Client client);

    Account findById(long customerId);

}
