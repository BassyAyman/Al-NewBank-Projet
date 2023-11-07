package newbankg.webtransactionservice.interfaces.accountbusiness;

public interface IBalanceChecker {

    boolean isBalanceOk(int ammountToPay,int clientSolde);

}
