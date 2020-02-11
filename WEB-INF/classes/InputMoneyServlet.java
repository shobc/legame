import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
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
        int pay = Integer.parseInt(req.getParameter("money"));
        String RandomString = req.getParameter("RandomString");
        ServletContext  sc = getServletContext();
        System.out.println("RandomString"+RandomString);
        System.out.println("sc.getAttribute((RandomString)"+sc.getAttribute(RandomString));
        pb.setUser_id(String.valueOf(sc.getAttribute(RandomString)));
        pb.setMoney(pay);

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        PropertyDao dao = factory.getOraPropertyDao();
        dao.addPropery(pb);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        res.sendRedirect("clear.jsp");
    }
}