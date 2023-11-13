package fr.igorbanque.updateservice.componets;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import fr.igorbanque.updateservice.models.Account;
import fr.igorbanque.updateservice.models.CreditCard;
import fr.igorbanque.updateservice.models.Transaction;
import fr.igorbanque.updateservice.repositories.AccountRepository;
import fr.igorbanque.updateservice.repositories.CreditCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaListeners {

    @Autowired
    public AccountRepository accountRepository;
    @Autowired
    public CreditCardRepository creditCardRepository;

    private static final Logger LOGGER = Logger.getLogger(KafkaListeners.class.getSimpleName());

    @KafkaListener(topics = "transactionWrite", groupId = "write")
    void listenerOnTransaction(String transaction){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        Transaction transactionObj;
        try {
            // Client account update
            transactionObj = objectMapper.readValue(transaction, Transaction.class);
            CreditCard clientCard = creditCardRepository.findCreditCardByCreditCardNumber(transactionObj.getCreditCardNumber());
            Account clientAccount = accountRepository.findAccountByClientAccount(clientCard.getClientInformation());
            clientAccount.setAmountMoney(clientAccount.getAmountMoney() - transactionObj.getAmountOfTransaction());
            accountRepository.save(clientAccount); // Client account will be updated in db
        } catch (Exception e){
            LOGGER.info("[ERROR] something went wrong : " + e.getMessage());
            return;
        }
        LOGGER.info("Transaction reçu avec succes:" + transactionObj);
    }

}
