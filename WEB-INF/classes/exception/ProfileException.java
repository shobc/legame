package exception;

public class ProfileException extends SystemException{
    public ProfileException(String mess,Throwable cause){
        super(mess,cause);
    }
    public ProfileException(String mess){
        super(mess);
    }
}