package fr.igorbanque.updateservice.componets;

import fr.igorbanque.updateservice.models.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "transactionWrite")
    void listenerOnTransaction(Transaction transaction){
        // TODO Store to master database
    }

}
