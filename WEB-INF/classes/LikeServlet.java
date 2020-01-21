import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import bean.TimeLineBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;


public class LikeServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String id = req.getParameter("timeline_id");
        String likeJudge = req.getParameter("likeJudge");
        String user_id = ub.getUser_id();
        TimeLineBean tlb = new TimeLineBean();
        tlb.setUser_id(user_id);
        tlb.setTimeline_id(id);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getOraTimeLineDao();
        if(likeJudge.equals("0")){
            dao.addTimeLineLike(tlb);
        }else{
            dao.deleteTimeLineLike(tlb);
        }
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }

}