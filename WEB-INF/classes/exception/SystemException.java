package exception;

public class SystemException extends RuntimeException{
    public SystemException(String mess,Throwable cause){
        super(mess,cause);
    }
    public SystemException(String mess){
        super(mess);
    }
}