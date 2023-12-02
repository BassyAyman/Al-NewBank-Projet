package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.redisrepo.RedisFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class RedisUpdater {
    private static final Logger LOGGER = Logger.getLogger(RedisUpdater.class.getSimpleName());

    @Autowired
    RedisFunction redisFunction;

    /**
     * save the new client debit in redis DB
     */
    public void setNewDebitValueForClient(long clientCreditCardNumber, int newDebit) {
        redisFunction.save(clientCreditCardNumber ,newDebit);
    }
}
