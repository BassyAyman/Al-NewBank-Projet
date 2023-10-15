package newbankg.terminaltransactionverificationservice.components;

import newbankg.terminaltransactionverificationservice.interfaces.ITerminalAccountInformation;
import newbankg.webtransactionservice.models.Account;
import org.springframework.stereotype.Component;

@Component
public class TerminalAccountInformationImpl implements ITerminalAccountInformation {

    @Override
    public Account getAccountFromTerminalId(long terminalId) {
        return new Account("123456789", "Mock", 1000L);
    }
}