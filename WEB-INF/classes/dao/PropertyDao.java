package dao;

import java.util.List;
import bean.PropertyBean;
import bean.BalanceBean;

public interface PropertyDao{
    void addPropery(PropertyBean p);
    BalanceBean getBalanceProperty(String id);
    void employMoney(PropertyBean p);
    List getAllProperty(String id);
    void updateQRCode(String user_id,String randomString);
}