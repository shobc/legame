package exception;

public class DeleteTimeLineException extends TimeLineException{
    public DeleteTimeLineException(String mess,Throwable cause){
        super(mess,cause);
    }
    public DeleteTimeLineException(String mess){
        super(mess);
    }
}