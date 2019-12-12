package dao;

import bean.TimeLineBean;
import java.util.ArrayList;

public interface TimeLineDao{
    void addTimeline(TimeLineBean tb);
    void addTimeLineLike(TimeLineBean tb);
    void deleteTimeLineLike(TimeLineBean tb);
    ArrayList getTimeLines(String user_id);
    TimeLineBean getTimeLine(String timeline_id);
}