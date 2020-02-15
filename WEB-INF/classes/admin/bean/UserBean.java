package admin.bean;

import java.io.Serializable;

public class UserBean implements Serializable{
    private String user_id;
    private String search_id;
    private String top_picture;
    private String stop_flag;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setSearch_id(String search_id) {
        this.search_id = search_id;
    }

    public String getSearch_id() {
        return search_id;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public String getTop_picture() {
        return top_picture;
    }

    public String getStop_flag() {
        return stop_flag;
    }

    public void setStop_flag(String stop_flag) {
        this.stop_flag = stop_flag;
    }
}