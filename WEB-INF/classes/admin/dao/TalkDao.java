package admin.dao;

import java.util.ArrayList;

public interface TalkDao{
    ArrayList getUserTalk(String chat_id);
    void sendMessage(String user_id);
}