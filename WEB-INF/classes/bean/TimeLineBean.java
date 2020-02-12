package bean;

import java.io.Serializable;
import java.util.ArrayList;

public class TimeLineBean implements Serializable{
    private String user_id;
    private String name;
    private String top_picture;
    private String timeline_id;
    private String timeline_sentence;
    private String timeline_time;
    private ArrayList timeline_picutre = new ArrayList();
    private String timeline_like_id;
    private String like_count;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setTimeline_id(String timeline_id) {
        this.timeline_id = timeline_id;
    }

    public String getTimeline_id() {
        return timeline_id;
    }

    public String getTop_picture() {
        return top_picture;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public void setTimeline_sentence(String timeline_sentence) {
        this.timeline_sentence = timeline_sentence;
    }

    public String getTimeline_sentence() {
        return timeline_sentence;
    }

    public void setTimeline_time(String timeline_time) {
        this.timeline_time = timeline_time;
    }

    public String getTimeline_time() {
        return timeline_time;
    }

    public String getTimeline_like_id() {
        return timeline_like_id;
    }

    public void setTimeline_like_id(String timeline_like_id) {
        this.timeline_like_id = timeline_like_id;
    }

    public ArrayList getTimeline_picutre() {
        return timeline_picutre;
    }

    public void setTimeline_picutre(ArrayList timeline_picutre) {
        this.timeline_picutre = timeline_picutre;
    }

    public void add(TimeLinePictureBean tlpb){
        timeline_picutre.add(tlpb);
    }

    public String getLike_count() {
        return like_count;
    }

    public void setLike_count(String like_count) {
        this.like_count = like_count;
    }
}