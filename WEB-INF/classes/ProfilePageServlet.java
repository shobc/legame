import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import DB.FriendSearchListDB;
import DB.NewFriendSearchDB;

//profile画面に必要なサーブレット
public class ProfilePageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        System.out.println("user_id="+user_id);
        //友達リストを取得するためのクラス
        FriendSearchListDB friendsearchlistdb = new FriendSearchListDB();
        ArrayList friendList = friendsearchlistdb.searchFriendProfile(user_id);
        //新しい友達がいるかを数で取得
        NewFriendSearchDB newfriendsearchdb = new NewFriendSearchDB();
        String noticeCount = newfriendsearchdb.searchNewFriend(user_id);
        //値をセットする
        req.setAttribute("friendList",friendList);
        req.setAttribute("noticeCount",noticeCount);
        //URLを指定する
        RequestDispatcher dis = req.getRequestDispatcher("profile");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
