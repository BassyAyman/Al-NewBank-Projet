package newbankg.webtransactionservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
public class WebTransactionController {

    private static final Logger LOGGER = Logger.getLogger(WebTransactionController.class.getName());

    @GetMapping(path = "/")
    public ResponseEntity<String> checkHealth() {
        useRessource();
        LOGGER.info("GERMANY Web Transaction Service is up and running");
        return ResponseEntity.ok("GERMANY Web Transaction Service is up and running");
    }

    void useRessource() {
        for (int i = 0; i < 10000; i++) {
            boolean isModuloFive = i % 5 == 0;
        }
    }
}
