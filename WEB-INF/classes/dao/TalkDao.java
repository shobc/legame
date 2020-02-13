package dao;

import bean.TalkBean;
import java.util.ArrayList;

public interface TalkDao{
    void addRead_flag(String chat_id,String user_id);
    ArrayList getTalk(String chat_id);
    boolean getBlockJudge(String chat_id,String user_id);
    void addTalk(TalkBean tb);
//    void addTalkPicture(String talk_id,String imagePath);
//    ArrayList getPicture(String chat_id);
    boolean blockJudge(String chat_id);
    void addTalkPicture(TalkBean tb);
}