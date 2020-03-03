package adminShop.exception;

public class RegisterAccountException extends ShopAdminAccountException{
    public RegisterAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public RegisterAccountException(String mess){
        super(mess);
    }
}