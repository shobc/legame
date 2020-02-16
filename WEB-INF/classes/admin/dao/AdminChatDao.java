package admin.dao;

import admin.bean.ChatBean;
import java.util.ArrayList;

public interface AdminChatDao{
    ArrayList getReportChatList();
    boolean getJudge(int flag,String user_id);
    void addChat(int flag,String user_id);
    void releaseChat(String chat_id);
}