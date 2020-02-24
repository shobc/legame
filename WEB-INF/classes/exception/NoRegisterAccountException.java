package exception;

public class NoRegisterAccountException extends AccountException{
    public NoRegisterAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NoRegisterAccountException(String mess){
        super(mess);
    }
}