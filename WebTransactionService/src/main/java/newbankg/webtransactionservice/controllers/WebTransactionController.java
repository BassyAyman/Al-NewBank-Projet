package newbankg.webtransactionservice.controllers;

import newbankg.webtransactionservice.InvalidTransactionException;
import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import newbankg.webtransactionservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @RestController
    public class WebTransactionController {

        private static final Logger LOGGER = Logger.getLogger(WebTransactionController.class.getName());

        @Autowired
        ITransactionValidator transactionValidator;

        @PostMapping(path = "payOnline", consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<String> payOnline(@RequestBody Transaction transaction) {
            try {
                Transaction validatedTransaction = transactionValidator.makeTransactionWithCardId(transaction);
                // TODO: send transaction somewhere...
                return ResponseEntity.ok("Transaction successful");
            } catch (InvalidTransactionException e) {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(e.getMessage());
            } catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
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
}
