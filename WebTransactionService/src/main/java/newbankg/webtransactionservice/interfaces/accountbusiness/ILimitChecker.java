package newbankg.webtransactionservice.interfaces.accountbusiness;

public interface ILimitChecker {

    boolean checkLimit(int amountToPay, int balance);

}
