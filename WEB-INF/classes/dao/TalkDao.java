package dao;

import bean.TalkBean;
import java.util.ArrayList;

public interface TalkDao{
    ArrayList getTalk(String chat_id);
    void addTalk(TalkBean tb);
}