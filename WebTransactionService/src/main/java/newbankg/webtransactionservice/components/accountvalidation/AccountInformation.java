package newbankg.webtransactionservice.components.accountvalidation;

import newbankg.webtransactionservice.interfaces.accountbusiness.IAccountInformation;
import newbankg.webtransactionservice.models.Account;
import newbankg.webtransactionservice.models.Transaction;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
import java.util.Optional;

@Component
public class AccountInformation implements IAccountInformation {

    @Override
    public Account getAccountFromCardId(Transaction transaction) {
        Optional<Account> accountOpt = findAccountByCardId(transaction.clientCreditCardNumber());

        return accountOpt.orElseThrow(() -> new NoSuchElementException("Card not found"));
    }

    private Optional<Account> findAccountByCardId(String clientCreditCartNumber) {
        //return AccountRepository.accounts.stream()
        //        .filter(account -> account.getCreditCardNumber().equals(clientCreditCartNumber))
        //        .findFirst();
        return Optional.empty();
    }
}
