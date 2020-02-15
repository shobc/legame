package admin.bean;

import java.io.Serializable;

public class TimeLinePictureBean implements Serializable{
    private String timeline_id;
    private String base64Image;

    public void setTimeline_id(String timeline_id) {
        this.timeline_id = timeline_id;
    }

    public String getTimeline_id() {
        return timeline_id;
    }

    public String getBase64Image() {
        return base64Image;
    }

    public void setBase64Image(String base64Image) {
        this.base64Image = base64Image;
    }
}