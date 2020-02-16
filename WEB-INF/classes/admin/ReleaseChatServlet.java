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
import admin.dao.AdminChatDao;

public class ReleaseChatServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String chat_id = req.getParameter("chat_id");
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        AdminChatDao dao = factory.getAdminChatDao();
        dao.releaseChat(chat_id);
        res.sendRedirect("ReportChatListServlet");
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}