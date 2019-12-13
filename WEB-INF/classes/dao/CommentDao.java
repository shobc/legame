package dao;

//コメントに関するデータを書くのするためのBeanファイル
import bean.CommentBean;
import java.util.ArrayList;

//タイムラインのコメント用のDaoのインターフェイス
public interface CommentDao{
    //タイムラインのコメントを追加する
    void addComment(CommentBean cb);
    //タイムラインのコメント一覧を表示する
    ArrayList getComment(String timeline_id);
}