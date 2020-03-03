package adminShop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.UserDao;
import adminShop.exception.NotUserQRCodeException;
//import adminShop.exception.NotMoneyException;

@WebServlet("/shopAdmin/PayServlet")
public class PayServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String randomString = req.getParameter("RandomString");
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        UserDao dao = factory.getUserDao();
        if(dao.judgeQRCode(randomString)){
            throw new NotUserQRCodeException("ìoò^Ç≥ÇÍÇƒÇ¢Ç»Ç¢QRÉRÅ[ÉhÇ≈Ç∑");
        }
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/settlement.jsp");
        dis.forward(req,res);
    }
}