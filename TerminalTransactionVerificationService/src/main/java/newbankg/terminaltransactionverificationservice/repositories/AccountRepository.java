package newbankg.terminaltransactionverificationservice.repositories;

import newbankg.terminaltransactionverificationservice.models.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findById(long id);

}
