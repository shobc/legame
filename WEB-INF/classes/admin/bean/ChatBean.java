package admin.bean;

import java.io.Serializable;

public class ChatBean implements Serializable{
    private String chat_id;
    private String name;
    private String top_picture;

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
}