package admin.dao;

import admin.bean.AdminUserBean;

public interface AdminUserDao{
    boolean getAdminUserJudge(AdminUserBean aub);
    AdminUserBean getAdminUser(AdminUserBean aub);
}