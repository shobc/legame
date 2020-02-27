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
    ArrayList getOneTimelinePicture(String timeline_id);
    String getCountNotice(String user_id);
    ArrayList getCommentNotice(String user_id);
    void updateCommentNotice(String user_id);
    ArrayList getFriendTimeLines(String user_id,String friend_id);
    ArrayList getFriendTimelinePicture(String user_id,String friend_id);
    void deleteTimeLine(String timeline_id);
    ArrayList getMyTimeLines(String user_id);
    ArrayList getMyTimelinePicture(String user_id);
    boolean reportJudgeTimeLine(TimeLineBean tb);
    void addReportTimeLine(TimeLineBean tb);
    int getReportCountTimeLine(String timeline_id);
    boolean deleteJudgeTimeline(String timeline_id);
}