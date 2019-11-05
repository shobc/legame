package Bean;

import java.io.Serializable;
public class UserBean implements Serializable{
    private String user_id;
    private String search_id;
    private String name;
    private String single_word;
    private String top_picture;

    public String getUser_id() {
        return user_id;
    }
    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getSearch_id() {
        return search_id;
    }

    public void setSearch_id(String search_id) {
        this.search_id = search_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getSingle_word() {
        return single_word;
    }

    public void setSingle_word(String single_word) {
        this.single_word = single_word;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public String getTop_picture() {
        return top_picture;
    }
}