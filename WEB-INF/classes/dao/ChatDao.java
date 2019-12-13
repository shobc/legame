package dao;

//チャットに関するデータを書くのするためのBeanファイル
import bean.ChatBean;
import java.util.ArrayList;

//チャット用のDaoのインターフェイス
public interface ChatDao{
    //チャットを追加する
    void addChat(ChatBean cb);
    //チャットが作られているか判定する
    boolean getJudge(ChatBean cb);
    //チャットのIdを取得する
    String getChatId(ChatBean cb);
    //チャット一覧を取得する
    ArrayList getChat(String user_id);
}