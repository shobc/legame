import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.OracleConnectionManager;
import bean.PropertyBean;
import dao.AbstractDaoFactory;
import dao.PropertyDao;

@WebServlet("/SettlementServlet")
public class SettlementServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String history = req.getParameter("history");
        String price = req.getParameter("price");
        String randomString = req.getParameter("qrcode");

        PropertyBean pb = new PropertyBean();
        pb.setHistory(history);
        pb.setMoney(Integer.parseInt(price));
        pb.setRandomString(randomString);

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        PropertyDao dao = factory.getOraPropertyDao();
        dao.employMoney(pb);

        RequestDispatcher dis = req.getRequestDispatcher("clear");
        dis.forward(req,res);
    }
}