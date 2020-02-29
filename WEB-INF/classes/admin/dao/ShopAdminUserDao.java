package admin.dao;

import java.util.ArrayList;

public interface ShopAdminUserDao{
    ArrayList getShopAdminApprovalList();
    void deleteShopAdmin(String shop_admin_user_id);
    void addShopAdminApproval(String shop_admin_user_id);
}