package exception;

public class SufferMailException extends NoLoginException{
    public SufferMailException(String mess,Throwable cause){
        super(mess,cause);
    }
    public SufferMailException(String mess){
        super(mess);
    }
}