package adminShop.dao;

public class ShopAdminOraDaoFactory extends ShopAdminAbstractDaoFactory{
    public ShopAdminUserDao getShopAdminUserDao(){
        return new OraShopAdminUserDao();
    }
    public ShopAdminPropertyDao getShopAdminPropertyDao(){
        return new OraShopAdminPropertyDao();
    }
    public UserDao getUserDao(){
        return new OraUserDao();
    }
}