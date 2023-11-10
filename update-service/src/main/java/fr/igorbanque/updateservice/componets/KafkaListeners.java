package fr.igorbanque.updateservice.componets;

import fr.igorbanque.updateservice.models.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class KafkaListeners {

    private static final Logger LOGGER = Logger.getLogger(KafkaListeners.class.getSimpleName());

    @KafkaListener(topics = "transactionWrite", groupId = "write")
    void listenerOnTransaction(String transaction){
        LOGGER.info("Transaction reçu :" + transaction);
        // TODO Store to master database
    }

}
