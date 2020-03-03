package exception;

public class NotMailException extends AccountException{
    public NotMailException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NotMailException(String mess){
        super(mess);
    }
}