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

//profile��ʂɕK�v�ȃT�[�u���b�g
public class ProfilePageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        //�F�B���X�g���擾���邽�߂̃N���X
        FriendSearchListDB friendsearchlistdb = new FriendSearchListDB();
        ArrayList friendList = friendsearchlistdb.searchFriendProfile(user_id);
        //�V�����F�B�����邩�𐔂Ŏ擾
        NewFriendSearchDB newfriendsearchdb = new NewFriendSearchDB();
        String noticeCount = newfriendsearchdb.searchNewFriend(user_id);
        //�l���Z�b�g����
        req.setAttribute("friendList",friendList);
        req.setAttribute("noticeCount",noticeCount);
        //URL���w�肷��
        RequestDispatcher dis = req.getRequestDispatcher("profile");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
