package newbankg.webtransactionservice.repositories;

import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findAccountByClientAccount(Client client);

    Account findById(long customerId);

}
