package newbankg.webtransactionservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication
@EnableRedisRepositories(basePackages = "newbankg.webtransactionservice.redisrepo")
public class WebTransactionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebTransactionServiceApplication.class, args);
    }

}
