package newbankg.terminaltransactionverificationservice.interfaces;

public interface ILimitChecker {

    boolean checkLimit(int amountToPay, int balanceLimit);

}
