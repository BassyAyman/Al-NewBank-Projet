package newbankg.terminaltransactionverificationservice.services;

import newbankg.terminaltransactionverificationservice.interfaces.IAccountValidator;
import newbankg.terminaltransactionverificationservice.interfaces.ITransactionValidator;
import newbankg.terminaltransactionverificationservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalTransactionService implements ITransactionValidator {
    @Autowired
    IAccountValidator accountValidator;

    @Override
    public boolean makeTransaction(Account account, int amountOfTransaction) {
        return accountValidator.checkAccount(account, amountOfTransaction);
    }
}
