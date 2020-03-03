package adminShop.exception;

public class NotShopAdminAccountException extends ShopAdminAccountException{
    public NotShopAdminAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NotShopAdminAccountException(String mess){
        super(mess);
    }
}