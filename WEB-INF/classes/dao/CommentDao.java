package dao;

//コメントに関するデータを書くのするためのBeanファイル
import bean.CommentBean;
import java.util.ArrayList;

//タイムラインのコメント用のDaoのインターフェイス
public interface CommentDao{
    //コメントに対するいいね
    void addCommentLike(CommentBean cb);
    //コメントに対するいいねを削除する
    void deleteCommentLike(CommentBean cb);
    //タイムラインのコメントを追加する
    void addComment(CommentBean cb);
    //タイムラインのコメント一覧を表示する
    ArrayList getComment(CommentBean cb);
}