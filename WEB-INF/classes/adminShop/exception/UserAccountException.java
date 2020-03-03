package adminShop.exception;

public class UserAccountException extends RuntimeException{
    public UserAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public UserAccountException(String mess){
        super(mess);
    }
}