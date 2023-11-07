package fr.igorbanque.retrieverservice.repositories;

import fr.igorbanque.retrieverservice.modeles.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findbyClientLastName(String lastName);
}
