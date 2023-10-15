package newbankg.terminaltransactionverificationservice.controllers;


import newbankg.terminaltransactionverificationservice.models.Transaction;
import newbankg.terminaltransactionverificationservice.services.TerminalTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TerminalTransactionController {

    @Autowired
    private TerminalTransactionService terminalTransactionService;

    @PostMapping(path = "/payOnline", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processTransaction(@RequestBody Transaction transaction) {

        if (terminalTransactionService.makeTransactionWithCardId(transaction.cardId())) {
            String response = sendTransactionToService(transaction);
            if (response.equals("Transaction is valid")) {
                return ResponseEntity.ok("Transaction is valid");
            }
            System.out.println("Transaction is valid but terminal transaction service is not available");
            System.out.println("response was " + response + " for transaction " + transaction.toString() + " expected response was Transaction is valid");
            return ResponseEntity.badRequest().body("Transaction is valid but terminal transaction service is not available");
        }

        return ResponseEntity.badRequest().body("Transaction is not valid");
    }

    private String sendTransactionToService(Transaction transaction) {
        RestTemplate restTemplate = new RestTemplate();
        String serviceUrl = "http://localhost:8080/transaction";
        ResponseEntity<String> response = restTemplate.postForEntity(serviceUrl, transaction, String.class);

        return response.getBody();
    }
}