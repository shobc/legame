package exception;

public class IntegrationException extends RuntimeException{
    public IntegrationException(String mess,Throwable cause){
        super(mess,cause);
    }
    public IntegrationException(String mess){
        super(mess);
    }
}