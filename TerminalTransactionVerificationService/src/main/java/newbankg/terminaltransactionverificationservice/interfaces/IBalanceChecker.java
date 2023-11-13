package newbankg.terminaltransactionverificationservice.interfaces;

public interface IBalanceChecker {

    boolean isBalanceOk(int amountToPay, int clientBalance);

}
