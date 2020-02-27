package exception;

public class SufferNewRegisterSearchIdException extends ProfileException{
    public SufferNewRegisterSearchIdException(String mess,Throwable cause){
        super(mess,cause);
    }
    public SufferNewRegisterSearchIdException(String mess){
        super(mess);
    }
}