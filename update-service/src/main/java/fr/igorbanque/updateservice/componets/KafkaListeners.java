package fr.igorbanque.updateservice.componets;

import fr.igorbanque.updateservice.models.Transaction;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "transactionWrite", groupId = "write")
    void listenerOnTransaction(String transaction){
        System.out.println("Transaction reçu");
        // TODO Store to master database
    }

}
