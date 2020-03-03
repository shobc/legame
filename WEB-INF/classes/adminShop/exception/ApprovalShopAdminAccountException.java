package adminShop.exception;

public class ApprovalShopAdminAccountException extends ShopAdminAccountException{
    public ApprovalShopAdminAccountException(String mess,Throwable cause){
        super(mess,cause);
    }
    public ApprovalShopAdminAccountException(String mess){
        super(mess);
    }
}