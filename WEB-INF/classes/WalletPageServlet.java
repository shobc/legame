import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import bean.BalanceBean;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.PropertyDao;

//Wallet�y�[�W��\�����邽�߂̃T�[�u���b�g
public class WalletPageServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        PropertyDao dao = factory.getOraPropertyDao();

        List propertylist =dao.getAllProperty(user_id);

        BalanceBean bb = dao.getBalanceProperty(user_id);

        session.setAttribute("propertylist",propertylist);
        session.setAttribute("bb",bb);
        RequestDispatcher dis = req.getRequestDispatcher("wallet");
        dis.forward(req,res);
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doGet(req,res);
    }
}

