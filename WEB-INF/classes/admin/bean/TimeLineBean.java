package admin.bean;

import java.io.Serializable;
import java.util.ArrayList;

public class TimeLineBean implements Serializable{
    private String timeline_id;
    private String name;
    private String top_picture;
    private String time;
    private ArrayList timeline_picutre = new ArrayList();
    private String timeline_sentence;
    private String report_count;

    public String getTimeline_id() {
        return timeline_id;
    }

    public void setTimeline_id(String timeline_id) {
        this.timeline_id = timeline_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public String getTop_picture() {
        return top_picture;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTimeline_picutre(ArrayList timeline_picutre) {
        this.timeline_picutre = timeline_picutre;
    }

    public void setTimeline_sentence(String timeline_sentence) {
        this.timeline_sentence = timeline_sentence;
    }

    public ArrayList getTimeline_picutre() {
        return timeline_picutre;
    }

    public String getTimeline_sentence() {
        return timeline_sentence;
    }

    public void add(TimeLinePictureBean tlpb){
        timeline_picutre.add(tlpb);
    }

    public String getReport_count() {
        return report_count;
    }

    public void setReport_count(String report_count) {
        this.report_count = report_count;
    }
}