package newbankg.webtransactionservice.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

    @RestController
    public class WebTransactionController {

        @PostMapping(path = "payOnline", consumes = APPLICATION_JSON_VALUE)
        public ResponseEntity<String> evaluateOrbitState(@RequestBody Integer cardId){
            return ResponseEntity.ok("Card is ok");
        }

}
