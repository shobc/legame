package adminShop.exception;

public class NotMoneyException extends RuntimeException{
    public NotMoneyException(String mess,Throwable cause){
        super(mess,cause);
    }
    public NotMoneyException(String mess){
        super(mess);
    }
}