package fr.igorbanque.retrieverservice.controllers;

import fr.igorbanque.retrieverservice.controllers.dto.AccountInfoDto;
import fr.igorbanque.retrieverservice.controllers.dto.CardDto;
import fr.igorbanque.retrieverservice.modeles.Account;
import fr.igorbanque.retrieverservice.modeles.CreditCard;
import fr.igorbanque.retrieverservice.repositories.AccountRepository;
import fr.igorbanque.retrieverservice.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(path = DatabaseFacadeControllers.BASE_URI, produces = APPLICATION_JSON_VALUE)
@CrossOrigin
public class DatabaseFacadeControllers {

    static final String BASE_URI = "/";

    @Autowired
    private CreditCardRepository cardRepository;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping(path = "/retrieveCardInfo/{cardNumber}")
    public ResponseEntity<CardDto> retrieveCard(@PathVariable String cardNumber){
        CreditCard card = cardRepository.findByCreditCartNumber(cardNumber);
        if(card == null){
            return ResponseEntity.notFound().build();
        }
        CardDto cardDto = new CardDto(card.getCreditCartNumber(), card.getCreditCartDateExpiration(), card.getCvv());
        return ResponseEntity.ok(cardDto);
    }

    @GetMapping(path = "/retrieveClientAccountInfo/{clientLastName}")
    public ResponseEntity<AccountInfoDto> retrieveClientAccount(@PathVariable String clientLastName){
        Account account = accountRepository.findbyClientLastName(clientLastName);
        if(account == null){
            return ResponseEntity.notFound().build();
        }
        AccountInfoDto accountInfoDto =
                new AccountInfoDto(account.getAmountMoney(), account.getInDebitAmount(), account.getAccountLimit());
        return ResponseEntity.ok(accountInfoDto);
    }
}
