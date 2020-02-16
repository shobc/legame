package admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import admin.dao.AdminAbstractDaoFactory;
import admin.dao.TimeLineDao;
import admin.bean.TimeLineBean;
import admin.bean.TimeLinePictureBean;

public class ReportTimeLineServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getTimeLineDao();
        ArrayList timelineArray = dao.getReportTimeLine();
        ArrayList timelinePicList = dao.getReportTimelinePicture();
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
        req.setAttribute("caption","’Ê•ñ‚³‚ê‚½ƒ^ƒCƒ€ƒ‰ƒCƒ“");
        RequestDispatcher dis = req.getRequestDispatcher("/admin/home");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}