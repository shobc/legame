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
import admin.dao.AdminChatDao;

@WebServlet("/admin/ReportChatListServlet")
public class ReportChatListServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        AdminAbstractDaoFactory factory = AdminAbstractDaoFactory.getFactory();
        AdminChatDao dao = factory.getAdminChatDao();
        ArrayList chatList = dao.getReportChatList();
        req.setAttribute("chatList",chatList);
        RequestDispatcher dis = req.getRequestDispatcher("/admin/chat");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}