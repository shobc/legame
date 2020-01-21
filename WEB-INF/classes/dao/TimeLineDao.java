package dao;

import bean.TimeLineBean;
import java.util.ArrayList;

public interface TimeLineDao{
    String addTimeline(TimeLineBean tb);
    void addTimelinePicture(String timeline_id,String imagePath);
    void addTimeLineLike(TimeLineBean tb);
    void deleteTimeLineLike(TimeLineBean tb);
    ArrayList getTimeLines(String user_id);
    ArrayList getTimelinePicture(String user_id);
    TimeLineBean getTimeLine(TimeLineBean tb);
}