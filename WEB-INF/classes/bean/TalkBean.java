package bean;

import java.io.Serializable;
public class TalkBean implements Serializable{
    private String user_id;
    private String name;
    private String content;
    private String image;
    private String mess_time;
    private String top_picture;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public String getMess_time() {
        return mess_time;
    }

    public void setMess_time(String mess_time) {
        this.mess_time = mess_time;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public String getTop_picture() {
        return top_picture;
    }
}