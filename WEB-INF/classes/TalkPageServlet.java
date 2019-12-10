import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import DB.TalkSearchListDB;

public class TalkPageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String  chat_id = req.getParameter("chat_id");

        TalkSearchListDB talksearchlistdb = new TalkSearchListDB();
        ArrayList talkList = talksearchlistdb.searchTalkList(chat_id);
        req.setAttribute("talkList",talkList);
        req.setAttribute("chat_id",chat_id);
        RequestDispatcher dis = req.getRequestDispatcher("talkScreen");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
