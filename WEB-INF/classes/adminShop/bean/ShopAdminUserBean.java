package adminShop.bean;

import java.io.Serializable;
public class ShopAdminUserBean implements Serializable{
    private String shop_admin_user_id;
    private String user_name;
    private String mail;
    private String password;
    private String picture;
    private String random;

    public String getRandom() {
        return random;
    }

    public void setRandom(String random) {
        this.random = random;
    }

    public String getShop_admin_user_id() {
        return shop_admin_user_id;
    }

    public void setShop_admin_user_id(String shop_admin_user_id) {
        this.shop_admin_user_id = shop_admin_user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}