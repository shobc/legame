package admin.dao;

import java.util.ArrayList;

public interface UserDao{
    ArrayList getUserList();
    void stopUserAccount(String user_id);
    void releaseUserAccout(String user_id);
    ArrayList searchUser(String search_id);
}