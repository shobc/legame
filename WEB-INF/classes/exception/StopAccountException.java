package exception;

public class StopAccountException extends AccountException{
    public StopAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public StopAccountException(String mess){
        super(mess);
    }
}