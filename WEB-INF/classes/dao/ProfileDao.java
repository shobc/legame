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
}