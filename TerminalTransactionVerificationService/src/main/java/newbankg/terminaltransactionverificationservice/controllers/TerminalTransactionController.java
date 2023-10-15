package newbankg.terminaltransactionverificationservice.controllers;


import newbankg.terminaltransactionverificationservice.interfaces.ITerminalTransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TerminalTransactionController {

    @Autowired
    ITerminalTransactionValidator terminalTransactionValidator;

    @PostMapping(path = "payWithTerminal", consumes = APPLICATION_JSON_VALUE)
    public ResponseEntity<String> validateTerminalTransaction(@RequestBody long terminalId) {
        terminalTransactionValidator.makeTransactionWithTerminalId(terminalId);
        return ResponseEntity.ok("Terminal transaction is valid");
    }
}