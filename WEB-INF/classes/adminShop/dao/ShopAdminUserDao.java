package adminShop.dao;

import adminShop.bean.ShopAdminUserBean;

public interface ShopAdminUserDao{
    boolean getShopAdminUserJudge(ShopAdminUserBean saub);
    boolean getApprovalShopAdminUserJudge(ShopAdminUserBean saub);
    ShopAdminUserBean getShopAdminUser(ShopAdminUserBean saub);
    void provisionalRegisterShopAdminUser(ShopAdminUserBean saub);
    String getUser_id(String mail);
    void changePassWord(ShopAdminUserBean saub);
    void removeAccount(String shop_admin_user_id);
    boolean judgeRegisterMail(String mail);
    boolean emailJudge(String email);
}