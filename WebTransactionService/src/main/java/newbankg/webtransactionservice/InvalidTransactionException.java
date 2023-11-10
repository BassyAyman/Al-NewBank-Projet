package newbankg.webtransactionservice;

/**
 * Runtime exception thrown when a transaction is invalid.
 * Runtime exceptions are not checked by the compiler.
 */
public class InvalidTransactionException extends RuntimeException {

  public InvalidTransactionException(String message) {
    super(message);
  }
}
