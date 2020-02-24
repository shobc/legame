import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.ProfileDao;
import dao.TimeLineDao;
import bean.UserBean;
import bean.TimeLineBean;
import bean.TimeLinePictureBean;

@WebServlet("/ProfilePageServlet")
public class ProfilePageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String user_id = req.getParameter("id");

        UserBean ub = new UserBean();
        ub.setUser_id(user_id);
        HttpSession session = req.getSession();
        UserBean uub = (UserBean)session.getAttribute("ub");

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();
        TimeLineDao Tdao = factory.getOraTimeLineDao();

        ub = dao.getProfile(ub);
        ArrayList timelineArray = Tdao.getFriendTimeLines(uub.getUser_id(),user_id);
        ArrayList timelinePicList = Tdao.getFriendTimelinePicture(uub.getUser_id(),user_id);


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
        req.setAttribute("ub",ub);

        RequestDispatcher dis = req.getRequestDispatcher("profile");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}