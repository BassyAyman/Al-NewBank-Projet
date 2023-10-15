package newbankg.terminaltransactionverificationservice.components;

import newbankg.webtransactionservice.interfaces.ILimitChecker;
import org.springframework.stereotype.Component;

@Component
public class TerminalLimitChecker implements ILimitChecker {

    @Override
    public boolean checkLimit(long id) {
        return true; // mock
    }
}