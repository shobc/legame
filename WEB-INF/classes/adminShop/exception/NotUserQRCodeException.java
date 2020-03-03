package adminShop.exception;

public class NotUserQRCodeException extends UserAccountException{
    public NotUserQRCodeException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NotUserQRCodeException(String mess){
        super(mess);
    }
}