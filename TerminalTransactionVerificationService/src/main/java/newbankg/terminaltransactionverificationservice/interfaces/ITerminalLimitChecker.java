package newbankg.terminaltransactionverificationservice.interfaces;

public interface ITerminalLimitChecker {
    boolean checkLimitWithTerminalId(long terminalId);
}
