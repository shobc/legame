package exception;

public class SufferSearchIdException extends ProfileException{
    public SufferSearchIdException(String mess,Throwable cause){
        super(mess,cause);
    }
    public SufferSearchIdException(String mess){
        super(mess);
    }
}