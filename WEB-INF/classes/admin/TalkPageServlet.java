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
import admin.dao.TalkDao;

@WebServlet("/admin/TalkPageServlet")
public class TalkPageServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String chat_id = req.getParameter("chat_id");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        TalkDao dao = factory.getTalkDao();
        ArrayList talkList = dao.getUserTalk(chat_id);
        req.setAttribute("talkList",talkList);
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/admin/talk.jsp");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}