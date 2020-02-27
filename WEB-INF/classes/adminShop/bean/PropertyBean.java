package adminShop.bean;

import java.io.Serializable;

public class PropertyBean implements Serializable{
    private String property_id;
    private String shop_admin_user_id;
    private String user_id;
    private String history;
    private String date;
    private int money;
    private String RandomString;
    private String cancel_flag;

    public String getCancel_flag() {
        return cancel_flag;
    }

    public void setCancel_flag(String cancel_flag) {
        this.cancel_flag = cancel_flag;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setRandomString(String randomString) {
        RandomString = randomString;
    }

    public String getRandomString() {
        return RandomString;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getMoney() {
        return money;
    }

    public void setShop_admin_user_id(String shop_admin_user_id) {
        this.shop_admin_user_id = shop_admin_user_id;
    }

    public String getShop_admin_user_id() {
        return shop_admin_user_id;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getHistory() {
        return history;
    }

    public String getProperty_id() {
        return property_id;
    }

    public void setProperty_id(String property_id) {
        this.property_id = property_id;
    }
}