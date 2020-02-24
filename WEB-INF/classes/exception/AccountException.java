package exception;

public class AccountException extends RuntimeException{
    public AccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public AccountException(String mess){
        super(mess);
    }
}