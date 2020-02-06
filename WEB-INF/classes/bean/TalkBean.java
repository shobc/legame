package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class TalkBean implements Serializable{
    private String chat_id;
    private String chat1_id;
    private String user_id;
    private String talk_id;
    private String name;
    private String content;
    private String image;
    private ArrayList talk_picture = new ArrayList();
    private String mess_time;
    private String read_flag;
    private String top_picture;
    private String block_flag;

    public String getChat1_id() {
        return chat1_id;
    }

    public void setChat1_id(String chat1_id) {
        this.chat1_id = chat1_id;
    }

    public String getTalk_id() {
        return talk_id;
    }

    public void setTalk_id(String talk_id) {
        this.talk_id = talk_id;
    }

    public String getRead_flag() {
        return read_flag;
    }

    public void setRead_flag(String read_flag) {
        this.read_flag = read_flag;
    }

    public void add(TalkPictureBean tpb){
        talk_picture.add(tpb);
    }

    public ArrayList getTalk_picture() {
        return talk_picture;
    }

    public void setTalk_picture(ArrayList talk_picture) {
        this.talk_picture = talk_picture;
    }
    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setChat_id(String chat_id) {
        this.chat_id = chat_id;
    }

    public String getChat_id() {
        return chat_id;
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

    public void setBlock_flag(String block_flag) {
        this.block_flag = block_flag;
    }

    public String getBlock_flag() {
        return block_flag;
    }
}