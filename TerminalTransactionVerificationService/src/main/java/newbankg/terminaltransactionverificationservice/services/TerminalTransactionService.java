package newbankg.terminaltransactionverificationservice.services;

import newbankg.webtransactionservice.interfaces.IAccountValidator;
import newbankg.webtransactionservice.interfaces.ITransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TerminalTransactionService implements ITransactionValidator {
    @Autowired
    IAccountValidator accountValidator;

    @Override
    public boolean makeTransactionWithCardId(long cardId) {
        return accountValidator.checkAccountWithId(cardId);
    }
}
