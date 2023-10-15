package newbankg.webtransactionservice.controllers;

import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @RestController
    public class WebTransactionController {

        @Autowired
        ITransactionValidator transactionValidator;

        @PostMapping(path = "payOnline", consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<String> evaluateOrbitState(@RequestBody long cardId){
            transactionValidator.makeTransactionWithCardId(cardId);
            return ResponseEntity.ok("Card is ok");
        }

}
