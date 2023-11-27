package newbankg.webtransactionservice.models.redismodels;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@Data
@RedisHash("Debit")
public class Debit implements Serializable {
    @Id
    private String clientId;
    private int debit;
}
