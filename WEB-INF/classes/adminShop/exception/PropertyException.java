package adminShop.exception;

public class PropertyException extends RuntimeException{
    public PropertyException(String mess,Throwable cause){
        super(mess,cause);
    }
    public PropertyException(String mess){
        super(mess);
    }
}