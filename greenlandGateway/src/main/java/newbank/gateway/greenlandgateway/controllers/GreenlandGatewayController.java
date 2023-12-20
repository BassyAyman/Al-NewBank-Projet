package newbank.gateway.greenlandgateway.controllers;

import newbank.gateway.greenlandgateway.models.Account;
import newbank.gateway.greenlandgateway.models.CreditCard;
import newbank.gateway.greenlandgateway.repositories.AccountRepository;
import newbank.gateway.greenlandgateway.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class GreenlandGatewayController {

    private static final Logger LOGGER = Logger.getLogger(GreenlandGatewayController.class.getName());

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    CreditCardRepository creditCardRepository;

    @GetMapping(path = "creditCard")
    public ResponseEntity<CreditCard> getCreditCard(@RequestParam String clientCreditCardNumber){
        LOGGER.info("Gateway received call to retrieve credit card object");
        CreditCard creditCard = creditCardRepository.findByCreditCardNumber(clientCreditCardNumber);
        return ResponseEntity.ok(creditCard);
    }

    @GetMapping(path = "account")
    public ResponseEntity<Account> getAccount(@RequestParam String clientCreditCardNumber){
        LOGGER.info("Gateway received call to retrieve account object");
        CreditCard creditCard = creditCardRepository.findByCreditCardNumber(clientCreditCardNumber);
        Account account = accountRepository.findById(creditCard.getClientInformation().getCustomerIdentifier());
        return ResponseEntity.ok(account);
    }

}
