package adminShop.dao;

import adminShop.bean.PropertyBean;
import java.util.ArrayList;

public interface ShopAdminPropertyDao{
    ArrayList getMyShopHistory(String shop_admin_user_id);
    void addPropery(PropertyBean p);
    void employMoney(PropertyBean p);
    ArrayList getDateSort(PropertyBean p);
    ArrayList getDateSortCancel(PropertyBean p);
    void cancelItem(String property_id);
    void cancelAdd(String property_id);
    ArrayList getCancelList(String shop_admin_user_id);
}