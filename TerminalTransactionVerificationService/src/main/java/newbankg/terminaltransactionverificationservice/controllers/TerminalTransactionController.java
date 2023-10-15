package newbankg.terminaltransactionverificationservice.controllers;


import newbankg.terminaltransactionverificationservice.models.Transaction;
import newbankg.terminaltransactionverificationservice.services.TerminalTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TerminalTransactionController {

    @Autowired
    private TerminalTransactionService terminalTransactionService;

    @PostMapping("/newTransaction")
    public String newTransaction(@RequestBody Transaction transaction) {
        terminalTransactionService.createTransaction(transaction);
        return "Transaction created";
    }
}