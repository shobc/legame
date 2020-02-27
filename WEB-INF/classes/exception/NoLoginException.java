package exception;

public class NoLoginException extends SystemException{
    public NoLoginException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NoLoginException(String mess){
        super(mess);
    }
}