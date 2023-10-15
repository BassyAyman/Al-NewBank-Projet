package newbankg.webtransactionservice.interfaces;

import newbankg.webtransactionservice.models.Account;

public interface IAccountInformation {

    Account getAccountFromCardId(long cardId);

}
