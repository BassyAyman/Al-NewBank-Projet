package newbankg.webtransactionservice.controllers;

import newbankg.webtransactionservice.InvalidTransactionException;
import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import newbankg.webtransactionservice.models.Transaction;
import newbankg.webtransactionservice.models.redismodels.Debit;
import newbankg.webtransactionservice.redisrepo.RedisFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @RestController
    public class WebTransactionController {

        private static final Logger LOGGER = Logger.getLogger(WebTransactionController.class.getName());

        @Autowired
        ITransactionValidator transactionValidator;

        @Autowired
        RedisFunction redisFunction;

        @Autowired
        private KafkaTemplate<String, Transaction> stringKafkaTemplate;

        @PostMapping(path = "payOnline", consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<String> payOnline(@RequestBody Transaction transaction, @RequestParam String origin) {
            try {
                if (!System.getenv("REGION").equals(origin)) {
                    Transaction validatedTransaction = transactionValidator.makeTransactionWithGateway(transaction);
                    stringKafkaTemplate.send("transactionWrite", validatedTransaction);
                    LOGGER.info("Transaction successful: " + validatedTransaction.toString());
                    return ResponseEntity.ok("(GATEWAY) Transaction successful");
                }
                Transaction validatedTransaction = transactionValidator.makeTransactionWithCardId(transaction);
                stringKafkaTemplate.send("transactionWrite", validatedTransaction);
                LOGGER.info("Transaction successful: " + validatedTransaction.toString());
                return ResponseEntity.ok("Transaction successful");
            } catch (InvalidTransactionException e) {
                LOGGER.info("Transaction failed");
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
            } catch (NoSuchElementException e) {
                LOGGER.info("Transaction failed");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                LOGGER.info("Transaction failed");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + e);
            }
        }

        @GetMapping(path = "checkHealth")
        public ResponseEntity<String> checkHealth(){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LOGGER.info("WebTransactionService is ok");
            return ResponseEntity.ok("WebTransactionService is ok");
        }

        @PostMapping(path = "debit", consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<String> putDebitInRedis(@RequestBody Debit debit){
            try {
                redisFunction.save(Long.parseLong(debit.getClientId()), debit.getDebit());
                LOGGER.info("Debit saved in Redis");
                return ResponseEntity.ok("Debit saved in Redis");
            } catch (Exception e) {
                LOGGER.info("Debit failed to save in Redis");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error" + e);
            }
        }

        @GetMapping(path = "debit")
        public ResponseEntity<Integer> getDebitFromRedis(@RequestParam Long id){
            try {
                Integer debit = redisFunction.getClientDebitInContext(id).get();
                LOGGER.info("Debit retrieved from Redis");
                return ResponseEntity.ok(debit);
            } catch (Exception e) {
                LOGGER.info("Debit failed to retrieve from Redis + " + e);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
}
