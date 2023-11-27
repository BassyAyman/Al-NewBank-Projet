package newbankg.webtransactionservice.redisrepo;

import newbankg.webtransactionservice.models.redismodels.Debit;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebitRedisRepository extends CrudRepository<Debit, String> {
}
