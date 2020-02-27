package exception;

public class TimeLineException extends SystemException{
    public TimeLineException(String mess,Throwable cause){
        super(mess,cause);
    }
    public TimeLineException(String mess){
        super(mess);
    }
}