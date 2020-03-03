package adminShop.exception;

public class ShopAdminAccountException extends RuntimeException{
    public ShopAdminAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public ShopAdminAccountException(String mess){
        super(mess);
    }
}