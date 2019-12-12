package dao;

import bean.LoginUserBean;

public interface UserDao{
    public void RegisterUser(LoginUserBean lub);
    public void UpdateUserPassWord(LoginUserBean lub);
    public String getUserId(LoginUserBean lub);
}