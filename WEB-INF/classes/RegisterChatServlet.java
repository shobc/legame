import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import DB.ChatAddDB;
import DB.FriendSearchListDB;
import DB.ChatSearchDB;
import DB.ChatIdDB;
import Bean.UserBean;

public class RegisterChatServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String friend_id = req.getParameter("friend_id");
        HttpSession session = req.getSession();
        UserBean ub= (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        if(ChatSearchDB.getBooleanJudge(user_id,friend_id)){
            ChatAddDB chatadddb = new ChatAddDB();
            chatadddb.ChatAdd(user_id,friend_id);
        }
        String chat_id = ChatIdDB.getChatId(user_id,friend_id);
        res.sendRedirect("TalkPageServlet?chat_id="+chat_id);
    }
}