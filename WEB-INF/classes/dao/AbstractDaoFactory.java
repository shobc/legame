package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

//Oracle用のDaoDaoFactoryを呼び出すためのクラス
public abstract class AbstractDaoFactory{
    public static AbstractDaoFactory getFactory(){
        AbstractDaoFactory factory = null;
        Properties prop = new Properties();

        try{
            prop.load(new FileInputStream("c:\\legame\\dao.properties"));
            String name = prop.getProperty("dao");
            Class c = Class.forName(name);
            factory = (AbstractDaoFactory)c.newInstance();
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
    //お金関連のDaoクラスを格納する抽象メソッド
    public abstract PropertyDao getOraPropertyDao();
    //ユーザー関連のDaoクラスを格納する抽象メソッド
    public abstract UserDao getOraUserDao();
    //自分の情報関連のDaoクラスを格納する抽象メソッド
    public abstract ProfileDao getOraProfileDao();
    //タイムライン関連のDaoクラスを格納する抽象メソッド
    public abstract TimeLineDao getOraTimeLineDao();
    //タイムラインに対するコメント関連のDaoクラスを格納する抽象メソッド
    public abstract CommentDao getOraCommentDao();
    //チャット関連のDaoクラスを格納する抽象メソッド
    public abstract ChatDao getOraChatDao();
    //トーク関連のDaoクラスを格納する抽象メソッド
    public abstract TalkDao getOraTalkDao();
    //友達関連のDaoクラスを格納する抽象メソッド
    public abstract FriendDao getOraFriendDao();
}