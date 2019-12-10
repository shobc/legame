import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import DB.RegisterTalkDB;
import DB.TalkSearchListDB;
import Bean.UserBean;

public class RegisterTalkServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String chat_id = req.getParameter("chat_id");
        System.out.println(chat_id);
        String content = req.getParameter("content");
        System.out.println(content);
//        Part part = req.getPart("contentPicture");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        System.out.println("1");
        RegisterTalkDB registertalkdb = new RegisterTalkDB();
        System.out.println("2");
        registertalkdb.RegisterTalk(chat_id,ub.getUser_id(),content);

        TalkSearchListDB talksearchlistdb = new TalkSearchListDB();
        ArrayList talkList = talksearchlistdb.searchTalkList(chat_id);
        req.setAttribute("talkList",talkList);
        req.setAttribute("chat_id",chat_id);



        RequestDispatcher dis = req.getRequestDispatcher("talkScreen");
        dis.forward(req,res);
    }
//    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
//        this.doPost(req,res);
//    }
}
