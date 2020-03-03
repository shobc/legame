package admin.exception;

public class NotAdminAccountException extends AdminAccountException{
    public NotAdminAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NotAdminAccountException(String mess){
        super(mess);
    }
}