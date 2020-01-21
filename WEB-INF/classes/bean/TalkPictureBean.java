package bean;

import java.io.Serializable;

public class TalkPictureBean implements Serializable{
    private String talk_id;
    private String base64Image;

    public void setTalk_id(String talk_id) {
        this.talk_id = talk_id;
    }

    public String getTalk_id() {
        return talk_id;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}