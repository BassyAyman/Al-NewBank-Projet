package newbankg.terminaltransactionverificationservice.interfaces;

import newbankg.terminaltransactionverificationservice.models.Account;

public interface IAccountInformation {

    Account getAccountFromCardId(long cardId);

}
