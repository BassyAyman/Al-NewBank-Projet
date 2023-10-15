package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.ITerminalAccountInformation;
import newbankg.terminaltransactionverificationservice.interfaces.ITerminalAccountValidator;
import newbankg.terminaltransactionverificationservice.interfaces.ITerminalBalanceChecker;
import newbankg.terminaltransactionverificationservice.interfaces.ITerminalLimitChecker;
import newbankg.webtransactionservice.models.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TerminalAccountValidator implements ITerminalAccountValidator {

    @Autowired
    ITerminalAccountInformation terminalAccountInformation;

    @Autowired
    ITerminalLimitChecker terminalLimitChecker;

    @Autowired
    ITerminalBalanceChecker terminalBalanceChecker;

    @Override
    public boolean checkAccountWithTerminalId(long id) {
        Account terminalCustomer = terminalAccountInformation.getAccountFromTerminalId(id);
        return terminalLimitChecker.checkLimitWithTerminalId(id) &&
                terminalBalanceChecker.isTerminalBalanceOk(terminalCustomer);
    }
}