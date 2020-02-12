package dao;

//友達に関するデータを書くのするためのBeanファイル
import bean.FriendBean;
import java.util.ArrayList;

//友達用のDaoのインターフェイス
public interface FriendDao{
    //相手側が友達追加したかカウントする
    String getNewFriendCount(String user_id);
    //友達追加した相手を取得する
    ArrayList getNewFriend(String user_id);
    //友達をブロックしていたのを解除する
    void releaseBlockFriend(FriendBean fb);
    //友達をブロックする
    void blockFriend(FriendBean fb);
    //ブロックした友達を取得する
    ArrayList getBlockFriend(String user_id);
    //友達を削除する
    void deleteFriend(FriendBean fb);
    //友達を追加する
    void addFriend(FriendBean fb);
    //友達をsearch_idで検索をかけてプロフィールを取得する
    FriendBean getSearchFriend(FriendBean fb);
    //友達リストを取得する
    ArrayList getFriend(String user_id);

    FriendBean getFriendQRUser_id(String QRCode);

    FriendBean getFriendInfo(String friend_id);

    boolean getFriendAddJudge(String chat_id);

    boolean getFriendDeleteOrBlockJudge(String chat_id);

    void addFriend(String chat_id);

    void releaseFriend(String chat_id);

    void noFriendBlock(FriendBean fb);
}