import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DB.ChatAddDB;


public class RegisterChatSerlvet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String friend_id = req.getParameter("friend_id");
        HttpSession session = req.getSession();
        String user_id = (String)session.getAttribute("user_id");
        ChatAddDB chatadddb = new ChatAddDB();
        chatadddb.ChatAdd(user_id,friend_id);
        //トーク画面に飛ばすコードを記述する

        RequestDispatcher dis = req.getRequestDispatcher("profilePage");
        dis.forward(req,res);
    }
}