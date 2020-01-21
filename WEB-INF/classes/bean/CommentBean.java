package bean;

import java.io.Serializable;

public class CommentBean implements Serializable{
    private String comment_id;
    private String user_id;
    private String name;
    private String timeline_id;
    private String top_picture;
    private String comment_sentence;
    private String comment_time;
    private String comment_like_id;

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public void setTimeline_id(String timeline_id) {
        this.timeline_id = timeline_id;
    }

    public String getTimeline_id() {
        return timeline_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTop_picture() {
        return top_picture;
    }

    public void setTop_picture(String top_picture) {
        this.top_picture = top_picture;
    }

    public String getComment_sentence() {
        return comment_sentence;
    }

    public void setComment_sentence(String comment_sentence) {
        this.comment_sentence = comment_sentence;
    }

    public String getComment_time() {
        return comment_time;
    }

    public void setComment_time(String comment_time) {
        this.comment_time = comment_time;
    }

    public String getComment_like_id() {
        return comment_like_id;
    }

    public void setComment_like_id(String comment_like_id) {
        this.comment_like_id = comment_like_id;
    }
}