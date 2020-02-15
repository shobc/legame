package admin.dao;

public class AdminOraDaoFactory extends AdminAbstractDaoFactory{
    public AdminUserDao getAdminUserDao(){
        return new OraAdminUserDao();
    }
    public TimeLineDao getTimeLineDao(){
        return new OraTimeLineDao();
    }
    public AdminChatDao getAdminChatDao(){
        return new OraAdminChatDao();
    }
    public TalkDao getTalkDao(){
        return new OraTalkDao();
    }
    public UserDao getUserDao(){
        return new OraUserDao();
    }
}