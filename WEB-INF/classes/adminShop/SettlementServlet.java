package adminShop;

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

@WebServlet("/shopAdmin/SettlementServlet")
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
        req.setAttribute("title","åàçœäÆóπ");
        req.setAttribute("caption","éxï•Ç¢Ç™äÆóπÇµÇ‹ÇµÇΩ");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/clear.jsp");
        dis.forward(req,res);
    }
}