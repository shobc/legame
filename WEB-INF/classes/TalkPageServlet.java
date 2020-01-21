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
import bean.TalkBean;
import bean.UserBean;
import bean.TalkPictureBean;

public class TalkPageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String  chat_id = req.getParameter("chat_id");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TalkDao dao = factory.getOraTalkDao();
        dao.addRead_flag(chat_id,user_id);
        ArrayList talkArray = dao.getTalk(chat_id);
        boolean judge = dao.getBlockJudge(chat_id,user_id);
        ArrayList pictureList = dao.getPicture(chat_id);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        ArrayList talkList = new ArrayList();
        Iterator it = talkArray.iterator();
        TalkBean tb= null;
        while(it.hasNext()){
            tb = (TalkBean)it.next();
            Iterator itr = pictureList.iterator();
            while(itr.hasNext()){
                TalkPictureBean tpb = (TalkPictureBean)itr.next();
                if(tb.getTalk_id().equals(tpb.getTalk_id())){
                    tb.add(tpb);
                }
            }
            talkList.add(tb);
        }


        String inputText = "<input type='text' name='content'><input type='file' multiple='multiple' accept='image/*'  name='contentPicture'><input type='submit' value='送信'>";
        if(judge){
            inputText = "ユーザーをブロック中";
        }
        req.setAttribute("talkList",talkList);
        req.setAttribute("chat_id",chat_id);
        req.setAttribute("inputText",inputText);
        RequestDispatcher dis = req.getRequestDispatcher("talkScreen");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
