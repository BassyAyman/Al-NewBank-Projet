package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.ITerminalAccountValidator;
import newbankg.terminaltransactionverificationservice.interfaces.ITerminalTransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;
import java.util.logging.Logger;

@Component
public class TerminalTransactionValidationManager implements ITerminalTransactionValidator {

    private static final Logger LOGGER = Logger.getLogger(TerminalTransactionValidationManager.class.getSimpleName());

    @Autowired
    ITerminalAccountValidator terminalAccountValidator;

    @Override
    public void makeTransactionWithTerminalId(long terminalId) {
        LOGGER.log(Level.INFO, "Proceeding to terminal account validation");
        terminalAccountValidator.checkAccountWithTerminalId(terminalId);
    }
}