package adminShop.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//Oracle用のDaoDaoFactoryを呼び出すためのクラス
public abstract class ShopAdminAbstractDaoFactory{
    public static ShopAdminAbstractDaoFactory getFactory(){
        ShopAdminAbstractDaoFactory factory = null;
        Properties prop = new Properties();

        try{
            prop.load(new FileInputStream("c:\\legame\\adminShopDao.properties"));
            String name = prop.getProperty("dao");
            Class c = Class.forName(name);
            factory = (ShopAdminAbstractDaoFactory)c.newInstance();
        }catch(FileNotFoundException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(IOException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(InstantiationException e){
            throw new RuntimeException(e.getMessage(),e);
        }catch(IllegalAccessException e){
            throw new RuntimeException(e.getMessage(),e);
        }
        //具象クラスのインスタンスを返す
        return factory;
    }
    public abstract ShopAdminUserDao getShopAdminUserDao();
    public abstract ShopAdminPropertyDao getShopAdminPropertyDao();
}