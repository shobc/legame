package ajax;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import bean.UserBean;
import bean.TimeLineBean;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;

import admin.dao.AdminAbstractDaoFactory;
import admin.dao.TalkDao;
import admin.dao.AdminChatDao;

@WebServlet("/AjaxReportTimeLineServlet")
public class AjaxReportTimeLineServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String timeline_id = req.getParameter("timeline_id");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        TimeLineBean tb = new TimeLineBean();
        tb.setUser_id(ub.getUser_id());
        tb.setTimeline_id(timeline_id);
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getOraTimeLineDao();
        if(dao.reportJudgeTimeLine(tb)){
            dao.addReportTimeLine(tb);
            if(dao.getReportCountTimeLine(tb.getTimeline_id())>=5){
                //この中に入った時点で自動でそのユーザーにメッセージを送信する
                AdminAbstractDaoFactory adminFactory = AdminAbstractDaoFactory.getFactory();
                admin.dao.TimeLineDao TLdao = adminFactory.getTimeLineDao();
                AdminChatDao Cdao = adminFactory.getAdminChatDao();
                String user_id = TLdao.getUser_id(timeline_id);
                if(Cdao.getJudge(1,user_id)){
                    Cdao.addChat(1,user_id);
                }
                if(Cdao.getJudge(0,user_id)){
                    Cdao.addChat(0,user_id);
                }
                TalkDao Tdao = adminFactory.getTalkDao();
                Tdao.sendMessage(user_id);
                dao.deleteTimeLine(timeline_id);
            }
        }
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}