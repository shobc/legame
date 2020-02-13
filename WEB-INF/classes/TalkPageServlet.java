import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TalkDao;
import dao.ChatDao;
import dao.ProfileDao;
import dao.FriendDao;
import bean.TalkBean;
import bean.ChatBean;
import bean.UserBean;
import bean.TalkPictureBean;

public class TalkPageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String  chat_id = req.getParameter("chat_id");
        req.getSession(true);
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        session.setAttribute("sender_chat_id",chat_id);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TalkDao dao = factory.getOraTalkDao();
        ChatDao Cdao = factory.getOraChatDao();
        ProfileDao Pdao = factory.getOraProfileDao();
        FriendDao Fdao = factory.getOraFriendDao();
        String friendJudge = "";
        if(Fdao.getFriendAddJudge(chat_id)){
            friendJudge = "<button id='friendAdd' onclick='ajaxFriendAdd("+chat_id+")'>追加</button>";
        }else if(Fdao.getFriendDeleteOrBlockJudge(chat_id)){
            friendJudge = "<button id='frienddDeleteOrBlock' onclick='ajaxFriendRelease("+chat_id+")'>解除</button>";
        }
        session.setAttribute("receiver_chat_id",null);
        ChatBean cb = new ChatBean();
        cb.setUser_id(user_id);
        cb.setChat_id(chat_id);
        if(!Cdao.getJudge(cb)){
            session.setAttribute("receiver_chat_id",Cdao.getReceiverChatId(chat_id));
        }
        dao.addRead_flag(chat_id,user_id);
        ArrayList talkList = dao.getTalk(chat_id);
        boolean judge = dao.getBlockJudge(chat_id,user_id);
        req.setAttribute("yub",Pdao.getProfile(chat_id));
        ArrayList frieadList = Fdao.getFriend(user_id);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
        String inputText = "<input id='message' type='text'>\n" +
                "<input onclick='wsSendMessage();' value='Echo' type='button'>";
        if(judge){
            inputText = "ユーザーをブロック中";
        }
        req.setAttribute("talkList",talkList);
        req.setAttribute("chat_id",chat_id);
        req.setAttribute("inputText",inputText);
        req.setAttribute("friendJudge",friendJudge);
        req.setAttribute("frieadList",frieadList);
        RequestDispatcher dis = req.getRequestDispatcher("talkScreen");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
