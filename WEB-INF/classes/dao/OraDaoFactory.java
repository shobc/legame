package dao;

//AbstractDaoFactoryを継承している
public class OraDaoFactory extends AbstractDaoFactory{
    //お金関連のDaoクラスを格納する具象メソッド
    public PropertyDao getOraPropertyDao(){
        return new OraPropertyDao();
    }
    //ユーザー関連のDaoクラスを格納する具象メソッド
    public UserDao getOraUserDao(){
        return new OraUserDao();
    }
    //自分の情報関連のDaoクラスを格納する具象メソッド
    public ProfileDao getOraProfileDao(){
        return new OraProfileDao();
    }
    //タイムライン関連のDaoクラスを格納する具象メソッド
    public TimeLineDao getOraTimeLineDao(){return new OraTimeLineDao();}
    //タイムラインに対するコメント関連のDaoクラスを格納する具象メソッド
    public CommentDao getOraCommentDao(){return new OraCommentDao();}
    //チャット関連のDaoクラスを格納する具象メソッド
    public ChatDao getOraChatDao(){return new OraChatDao();}
    //トーク関連のDaoクラスを格納する具象メソッド
    public TalkDao getOraTalkDao(){return new OraTalkDao();}
    //友達関連のDaoクラスを格納する具象メソッド
    public FriendDao getOraFriendDao(){return new OraFriendDao();}
}