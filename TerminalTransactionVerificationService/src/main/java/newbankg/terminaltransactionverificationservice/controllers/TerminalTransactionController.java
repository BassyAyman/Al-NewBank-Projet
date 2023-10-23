package newbankg.terminaltransactionverificationservice.controllers;

import newbankg.terminaltransactionverificationservice.models.Account;
import newbankg.terminaltransactionverificationservice.models.Transaction;
import newbankg.terminaltransactionverificationservice.repositories.AccountRepository;
import newbankg.terminaltransactionverificationservice.services.TerminalTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TerminalTransactionController {

    private static Logger LOGGER = Logger.getLogger(TerminalTransactionController.class.getName());

    @Autowired
    private TerminalTransactionService terminalTransactionService;

    @Autowired
    private AccountRepository accountRepository;

    @PostMapping(path = "/payOnline", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processTransaction(@RequestBody Transaction transaction) {

        if (terminalTransactionService.makeTransactionWithCardId(transaction.cardId())) {
            String response = sendTransactionToService(transaction);
            if (response.equals("Transaction is valid")) {
                return ResponseEntity.ok("Transaction is valid");
            }
            System.out.println("Transaction is valid but terminal transaction service is not available");
            System.out.printf("response was %s for transaction %s expected response was Transaction is valid%n", response, transaction);
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

    @GetMapping(path = "checkHealth")
    public ResponseEntity<String> checkHealth(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.info("TerminalTransactionService is ok");
        return ResponseEntity.ok("TerminalTransactionService is ok");
    }

    /**
     * This method is used to add a mock user to the database
     * @return
     */
    @PutMapping(path = "/addMockUser")
    public ResponseEntity<String> addMockUser() {
        long randomId = (long) (Math.random() * 1000);
        Account account = accountRepository.save(new Account("John", "Doe", randomId));
        return ResponseEntity.ok("User added:" + account.toString());
    }
}