package newbankg.webtransactionservice.redisrepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RedisFunction {

    @Autowired
    private RedisTemplate<Long, Integer> redisTemplate;

    public void save(Long clientId, Integer debit) {
        redisTemplate.opsForValue().set(clientId, debit);
    }

    public Optional<Integer> getClientDebitInContext(Long clientId) {
        return redisTemplate.opsForValue().get(clientId) == null ?
                Optional.empty()
                :
                Optional.ofNullable(redisTemplate.opsForValue().get(clientId));
    }
}
