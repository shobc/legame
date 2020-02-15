package admin.dao;

import java.util.ArrayList;

public interface TimeLineDao{
    ArrayList getAllTimeLine();
    ArrayList getReportTimeLine();
    ArrayList getTimelinePicture();
    ArrayList getReportTimelinePicture();
    String getUser_id(String timeline_id);
    void DeleteTimeLine(String timeline_id);
}