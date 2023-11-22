package newbankg.webtransactionservice.components;

import jakarta.annotation.PostConstruct;
import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.Client;
import newbankg.webtransactionservice.models.CreditCard;
import newbankg.webtransactionservice.repositories.AccountRepository;
import newbankg.webtransactionservice.repositories.ClientRepository;
import newbankg.webtransactionservice.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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

        //add original john doe
        // addAccount(1, "John", "Doe", "4532759734545858", "12/25", "123");

        populateFromCSV("valid-credit-cards.csv");

    }

    public void populateFromCSV(String path) {
        String line = "";

        try {
            BufferedReader br = new BufferedReader(new FileReader(path));

            int count = 2; // Compteur de lignes
            while ((line = br.readLine()) != null) {
                addAccount(count, "John", "Doe", line, "12/25", "123");
                count++;
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void populateDumb(int numberOfClients) {
        for (int i = 2; i < numberOfClients+2; i++) {
            addAccount(i, "John", "Doe", "111111111111" + String.format("%04d", i), "12/25", "123");
        }
    }

    public void addAccount(long id, String firstName, String lastName, String creditCardNumber, String creditCardDateExpiration, String creditCardCvv) {
        Client client = new Client();
        client.setCustomerIdentifier(id);
        client.setFirstName(firstName);
        client.setLastName(lastName);
        clientRepository.save(client);

        Account account = new Account();
        account.setId(id);
        account.setClientAccount(client);
        account.setAmountMoney(1000);
        account.setInDebitAmount(0);
        account.setAccountLimit(1000);
        accountRepository.save(account);

        CreditCard creditCard = new CreditCard();
        creditCard.setId(id);
        creditCard.setClientInformation(client);
        creditCard.setCreditCardNumber(creditCardNumber);
        creditCard.setCreditCartDateExpiration(creditCardDateExpiration);
        creditCard.setCvv(creditCardCvv);
        creditCardRepository.save(creditCard);
    }
}
