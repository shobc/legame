package bean;

import java.io.Serializable;
public class FriendBean implements Serializable{
    private String user_id;
    private String name;
    private String single_word;
    private String top_picture;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
    public String getUser_id() {
        return user_id;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setSingle_word(String single_word) {
        this.single_word = single_word;
    }

    public String getSingle_word() {
        return single_word;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public String getTop_picture() {
        return top_picture;
    }
}