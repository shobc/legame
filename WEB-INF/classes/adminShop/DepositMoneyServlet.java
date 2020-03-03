package adminShop;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

import adminShop.exception.NotUserQRCodeException;

@WebServlet("/shopAdmin/DepositMoneyServlet")
public class DepositMoneyServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        int money = Integer.parseInt(req.getParameter("money"));
        String RandomString = req.getParameter("RandomString");
        ServletContext  sc = getServletContext();
        String user_id = (String)sc.getAttribute(RandomString);
        System.out.println("user_id"+user_id);
        if ((String)sc.getAttribute(RandomString)==null){
            throw new NotUserQRCodeException("ìoò^Ç≥ÇÍÇƒÇ¢Ç»Ç¢QRÉRÅ[ÉhÇ≈Ç∑");
        }

        req.setAttribute("money",money);
        req.setAttribute("RandomString",RandomString);
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/deposit-money.jsp");
        dis.forward(req,res);
    }
}