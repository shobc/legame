package bean;

import java.io.Serializable;
public class FriendBean implements Serializable{
    private String user_id;
    private String friend_id;
    private String search_id;
    private String name;
    private String single_word;
    private String top_picture;

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String frined_id) {
        this.friend_id = frined_id;
    }

    public void setSearch_id(String search_id) {
        this.search_id = search_id;
    }

    public String getSearch_id() {
        return search_id;
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