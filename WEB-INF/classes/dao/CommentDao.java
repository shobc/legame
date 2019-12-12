package dao;

import bean.CommentBean;
import java.util.ArrayList;

public interface CommentDao{
    void addComment(CommentBean cb);
    ArrayList getComment(String timeline_id);
}