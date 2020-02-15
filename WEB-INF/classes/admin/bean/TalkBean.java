package admin.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class TalkBean implements Serializable{
    private String search_id;
    private String top_picture;
    private String content;
    private String mess_time;


    public String getSearch_id() {
        return search_id;
    }

    public void setSearch_id(String search_id) {
        this.search_id = search_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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