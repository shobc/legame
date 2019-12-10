package dao;

import java.util.List;
import bean.PropertyBean;
import bean.BalanceBean;

public interface PropertyDao{
    public void addPropery(PropertyBean p);
    public BalanceBean getBalanceProperty(String id);
    public void employMoney(PropertyBean p);
    public List getAllProperty(String id);
}