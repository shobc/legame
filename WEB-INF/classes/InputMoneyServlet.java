import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

import bean.UserBean;
import bean.PropertyBean;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.PropertyDao;


//手入力チャージするためのサーブレット
public class InputMoneyServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        PropertyBean pb = new PropertyBean();
        int pay = Integer.parseInt(req.getParameter("pay"));
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        pb.setUser_id(user_id);
        pb.setMoney(pay);

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        PropertyDao dao = factory.getOraPropertyDao();
        dao.addPropery(pb);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        RequestDispatcher dis = req.getRequestDispatcher("WalletPageServlet");
        dis.forward(req,res);
    }
}