package dao;

import bean.LoginUserBean;

public interface UserDao{
    void RegisterUser(LoginUserBean lub);
    void UpdateUserPassWord(LoginUserBean lub);
    String getUserId(LoginUserBean lub);
    boolean searchPassWord(String old_pass,String user_id);
    void UpdateUserPassWord(String new_pass,String user_id);

}