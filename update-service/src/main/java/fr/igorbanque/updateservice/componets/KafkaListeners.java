package fr.igorbanque.updateservice.componets;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

    @KafkaListener(topics = "transactionWrite")
    void listenerOnTransaction(String transaction){

    }

}
