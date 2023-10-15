package newbankg.terminaltransactionverificationservice.services;
import newbankg.terminaltransactionverificationservice.interfaces.ITerminalTransactionService;
import newbankg.terminaltransactionverificationservice.models.Transaction;
import org.springframework.stereotype.Service;

@Service
public class TerminalTransactionService implements ITerminalTransactionService {
    @Override
    public void createTransaction(Transaction transaction) {

    }

    @Override
    public void validateTransaction(Transaction transaction) {

    }
}
