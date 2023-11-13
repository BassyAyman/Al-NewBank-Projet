package newbankg.terminaltransactionverificationservice.controllers;

import newbankg.terminaltransactionverificationservice.models.Account;
import newbankg.terminaltransactionverificationservice.models.Client;
import newbankg.terminaltransactionverificationservice.models.Transaction;
import newbankg.terminaltransactionverificationservice.repositories.AccountRepository;
import newbankg.terminaltransactionverificationservice.repositories.ClientRepository;
import newbankg.terminaltransactionverificationservice.services.TerminalTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TerminalTransactionController {

    private static final Logger LOGGER = Logger.getLogger(TerminalTransactionController.class.getName());

    @Autowired
    private TerminalTransactionService terminalTransactionService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    private static final String TRANSACTION_IS_VALID = "Transaction is valid";


    @PostMapping(path = "/payOnline", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> processTransaction(@RequestBody Transaction transaction) {
        if (null != transaction && terminalTransactionService.makeTransactionWithCardId(transaction.cardId(), transaction.amountOfTransaction())) {
            String response = sendTransactionToService(transaction);
            if (TRANSACTION_IS_VALID.equals(response)) { // null free
                LOGGER.info(TRANSACTION_IS_VALID);
                return ResponseEntity.ok(TRANSACTION_IS_VALID);
            }
            // LOGGER.info("Transaction is valid but terminal transaction service is not available");
            LOGGER.info("response was %s for transaction %s expected response was Transaction is valid%n".formatted(response, transaction));
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
    public ResponseEntity<String> checkHealth() {
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
     *
     * @return
     */
    @PutMapping(path = "/addJohnDoe")
    public ResponseEntity<String> addJohnDoe() {
        // LOGGER.info("Adding John Doe");
        try {
            Account account = accountRepository.save(new Account()); // TODO: fix this?
            LOGGER.info("Added John Doe");
            return ResponseEntity.ok("User added:%s".formatted(account.toString()));
        } catch (Exception e) {
            LOGGER.info("Could not add John Doe:" + e.getMessage());
            return ResponseEntity.badRequest().body("Could not add John Doe");
        }
    }

    @GetMapping(path = "/checkJohnDoe")
    public ResponseEntity<String> checkJohnDoe() {
        Client client = clientRepository.findByCustomerIdentifier(1L);
        if (client == null) {
            LOGGER.info("User not found");
            return ResponseEntity.badRequest().body("User not found");
        }
        if (!client.getFirstName().equals("John") || !client.getLastName().equals("Doe")) {
            LOGGER.info("User is not John Doe");
            return ResponseEntity.badRequest().body("User is not John Doe");
        }
        if (LOGGER.isLoggable(Level.INFO)) {
            LOGGER.info("User found:%s".formatted(client.toString()));
        }
        return ResponseEntity.ok("User found:%s".formatted(client.toString()));
    }
}