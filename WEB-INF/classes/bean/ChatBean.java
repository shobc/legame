package bean;

import java.io.Serializable;
public class ChatBean implements Serializable{
    private String user_id;
    private String friend_id;
    private String chat_id;
    private String name;
    private String top_picture;
    private String content;
    private String not_read_count;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getChat_id() {
        return chat_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public String getTop_picture() {
        return top_picture;
    }

    public String getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(String friend_id) {
        this.friend_id = friend_id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public String getNot_read_count() {
        return not_read_count;
    }

    public void setNot_read_count(String not_read_count) {
        this.not_read_count = not_read_count;
    }
}