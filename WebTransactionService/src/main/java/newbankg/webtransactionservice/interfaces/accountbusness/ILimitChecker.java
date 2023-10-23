package newbankg.webtransactionservice.interfaces.accountbusness;

public interface ILimitChecker {

    boolean checkLimit(int ammountToPay, int balance);

}
