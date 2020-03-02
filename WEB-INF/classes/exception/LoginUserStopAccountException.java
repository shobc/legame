package exception;

public class LoginUserStopAccountException extends AccountException{
    public LoginUserStopAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public LoginUserStopAccountException(String mess){
        super(mess);
    }
}