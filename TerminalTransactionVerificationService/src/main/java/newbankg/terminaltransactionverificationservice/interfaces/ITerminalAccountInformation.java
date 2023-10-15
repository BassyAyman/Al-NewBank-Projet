package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.webtransactionservice.models.Account;

public interface ITerminalAccountInformation {
    Account getAccountFromTerminalId(long terminalId);
}
