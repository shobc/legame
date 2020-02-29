package admin.bean;

import java.io.Serializable;
public class ShopAdminUserBean implements Serializable{
    private String shop_admin_user_id;
    private String user_name;
    private String mail;
    private String picture;

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

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}