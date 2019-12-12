package dao;

import bean.UserBean;


public interface ProfileDao{
    public UserBean getProfile(String mail,String pass);
    public void addProfile(UserBean ub);
    public UserBean getProfile(UserBean ub);
}