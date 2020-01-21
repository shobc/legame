import java.io.IOException;

import java.util.ArrayList;
import java.util.Iterator;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import bean.TimeLineBean;
import bean.TimeLinePictureBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;

public class TimeLineServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getOraTimeLineDao();

        ArrayList timelineArray = dao.getTimeLines(user_id);
        ArrayList timelinePicList = dao.getTimelinePicture(user_id);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        ArrayList timelineList = new ArrayList();
        Iterator it = timelineArray.iterator();
        TimeLineBean tlb= null;
        while(it.hasNext()){
            tlb = (TimeLineBean)it.next();
            Iterator itr = timelinePicList.iterator();
            while(itr.hasNext()){
                TimeLinePictureBean tlpb = (TimeLinePictureBean)itr.next();
                if(tlb.getTimeline_id().equals(tlpb.getTimeline_id())){
                    tlb.add(tlpb);
                }
            }
            timelineList.add(tlb);
        }

        req.setAttribute("timelineList",timelineList);
        RequestDispatcher dis = req.getRequestDispatcher("timeline");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doGet(req,res);
    }
}