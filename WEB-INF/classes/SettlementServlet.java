import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.Part;

import function.PathHolder;
import bean.PropertyBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.PropertyDao;

import function.ReadQR;


public class SettlementServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        PathHolder.pathName = getServletContext().getRealPath("/");
        String history = req.getParameter("history");
        String price = req.getParameter("price");
        String randomString = req.getParameter("qrcode");

        PropertyBean pb = new PropertyBean();
        pb.setHistory(history);
        pb.setMoney(Integer.parseInt(price));
        pb.setRandomString(randomString);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        PropertyDao dao = factory.getPropertyDao();
        dao.employMoney(pb);
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        RequestDispatcher dis = req.getRequestDispatcher("clear");
        dis.forward(req,res);
    }
}