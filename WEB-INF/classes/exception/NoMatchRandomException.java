package exception;

public class NoMatchRandomException extends NoLoginException{
    public NoMatchRandomException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NoMatchRandomException(String mess){
        super(mess);
    }
}