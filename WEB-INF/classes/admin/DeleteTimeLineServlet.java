package admin;

import java.util.ArrayList;
import java.util.Iterator;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import admin.dao.AdminAbstractDaoFactory;
import admin.dao.TimeLineDao;
import admin.dao.TalkDao;
import admin.dao.AdminChatDao;
import admin.function.CutURL;

@WebServlet("/admin/DeleteTimeLineServlet")
public class DeleteTimeLineServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String timeline_id = req.getParameter("timeline_id");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getTimeLineDao();
        if(dao.deleteTimeLineJudge(timeline_id)){
            AdminChatDao Cdao = factory.getAdminChatDao();
            String user_id = dao.getUser_id(timeline_id);
            dao.DeleteTimeLine(timeline_id);
            if(Cdao.getJudge(1,user_id)){
                Cdao.addChat(1,user_id);
            }
            if(Cdao.getJudge(0,user_id)){
                Cdao.addChat(0,user_id);
            }
            TalkDao Tdao = factory.getTalkDao();
            Tdao.sendMessage(user_id);
        }

        res.sendRedirect(CutURL.getPath(req.getHeader("REFERER")));
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}