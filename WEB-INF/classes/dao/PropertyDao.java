package dao;

import java.util.List;
import bean.PropertyBean;
import bean.BalanceBean;

//お金関連用のDaoのインターフェイス
public interface PropertyDao{
    //お金をチャージする
    void addPropery(PropertyBean p);
    //合計残高と合計ポイントを取得する
    BalanceBean getBalanceProperty(String id);
    //お金を使う
    void employMoney(PropertyBean p);
    //ユーザーのIDにあたる履歴を取得する
    List getAllProperty(String id);
    //QRの文字列を書き換える
    void updateQRCode(String user_id,String randomString);
}