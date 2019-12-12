package dao;

public class OraDaoFactory extends AbstractDaoFactory{
    public PropertyDao getOraPropertyDao(){
        return new OraPropertyDao();
    }
    public UserDao getOraUserDao(){
        return new OraUserDao();
    }
    public ProfileDao getOraProfileDao(){
        return new OraProfileDao();
    }
    public TimeLineDao getOraTimeLineDao(){return new OraTimeLineDao();}
    public CommentDao getOraCommentDao(){return new OraCommentDao();}
    public ChatDao getOraChatDao(){return new OraChatDao();}
}