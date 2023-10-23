package newbankg.webtransactionservice.controllers;

import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import newbankg.webtransactionservice.models.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @RestController
    public class WebTransactionController {

        private static Logger LOGGER = Logger.getLogger(WebTransactionController.class.getName());

        @Autowired
        ITransactionValidator transactionValidator;

        @PostMapping(path = "payOnline", consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<String> evaluateOrbitState(@RequestBody long cardId){
            transactionValidator.makeTransactionWithCardId(new Transaction());
            return ResponseEntity.ok("Card is ok");
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
