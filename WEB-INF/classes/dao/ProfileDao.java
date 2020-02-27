package dao;

//ログインしているユーザーのデータを保持するBean
import bean.UserBean;

//ログインユーザー用のDaoのインターフェイス
public interface ProfileDao{
    //ログイン時にユーザーのメールとパスでプロフィールを取得する
    UserBean getProfile(String mail,String pass);
    //新規登録してくるユーザーのプロフィールを追加する
    void addProfile(UserBean ub);
    //ログインユーザーのプロフィールを取得する
    UserBean getProfile(UserBean ub);
    //
    UserBean getProfile(String chat_id);
    //トップ画像を変更
    void updateTopPictureProfile(UserBean ub);
    //一言を変更
    void updateSingleWordProfile(UserBean ub);
    //名前を変更
    void updateNameProfile(UserBean ub);
    //ユーザー検索IDを変更
    void updateSearchIdProfile(UserBean ub);
    //プロフィールを変更
    void UpdateUserTopPicture(UserBean ub);

    void UpdateFriendQRCode(String user_id,String QRCode);

    boolean sufferSearchId(String search_id);

}