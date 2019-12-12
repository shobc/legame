package dao;

import bean.ChatBean;
import java.util.ArrayList;

public interface ChatDao{
    void addChat(ChatBean cb);
    boolean getJudge(ChatBean cb);
    String getChatId(ChatBean cb);
    ArrayList getChat(String user_id);
}