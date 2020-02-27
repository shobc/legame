package adminShop.dao;

import adminShop.bean.ShopAdminUserBean;

public interface ShopAdminUserDao{
    boolean getShopAdminUserJudge(ShopAdminUserBean saub);
    boolean getApprovalShopAdminUserJudge(ShopAdminUserBean saub);
    ShopAdminUserBean getShopAdminUser(ShopAdminUserBean saub);
    void ProvisionalRegisterShopAdminUser(ShopAdminUserBean saub);
}