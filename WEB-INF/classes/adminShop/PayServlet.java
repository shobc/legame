package adminShop;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

@WebServlet("/shopAdmin/PayServlet")
public class PayServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/settlement.jsp");
        dis.forward(req,res);
    }
}