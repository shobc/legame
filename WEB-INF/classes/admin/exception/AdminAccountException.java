package admin.exception;

public class AdminAccountException extends RuntimeException{
    public AdminAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public AdminAccountException(String mess){
        super(mess);
    }
}