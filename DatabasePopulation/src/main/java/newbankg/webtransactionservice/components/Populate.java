package newbankg.webtransactionservice.components;

import jakarta.annotation.PostConstruct;
import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.Client;
import newbankg.webtransactionservice.models.CreditCard;
import newbankg.webtransactionservice.models.Transaction;
import newbankg.webtransactionservice.repositories.AccountRepository;
import newbankg.webtransactionservice.repositories.ClientRepository;
import newbankg.webtransactionservice.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Populate {

    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public CreditCardRepository creditCardRepository;
    @Autowired
    public ClientRepository clientRepository;

    @PostConstruct
    public void populate() {
        System.out.println("Populating...");

        Client client = new Client();
        client.setCustomerIdentifier(1L);
        client.setFirstName("John");
        client.setLastName("Doe");
        clientRepository.save(client);

        Account account = new Account();
        account.setId(1L);
        account.setClientAccount(client);
        account.setAmountMoney(1000);
        account.setInDebitAmount(0);
        account.setAccountLimit(1000);
        accountRepository.save(account);

        CreditCard creditCard = new CreditCard();
        creditCard.setId(1L);
        creditCard.setClientInformation(client);
        creditCard.setCreditCardNumber("1234567891234567");
        creditCard.setCreditCartDateExpiration("12/25");
        creditCard.setCvv("123");
        creditCardRepository.save(creditCard);

//        Transaction transaction = new Transaction();
//        transaction.setClientFirstName("John");
//        transaction.setClientLastName("Doe");
//        transaction.setAmountOfTransaction(100);
//        transaction.setClientCreditCardNumber("1234567891234567");
//        transaction.setClientCreditCartDateExpiration("12/25");
//        transaction.setClientCVV("123");
    }
}
