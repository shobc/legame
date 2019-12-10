package dao;

import bean.UserBean;

public interface UserDao{
    public void RegisterUser(UserBean ub);
    public void UpdateUserPassWord(String id);
}