import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;

public class DepositMoneyServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        int money = Integer.parseInt(req.getParameter("money"));
        String RandomString = req.getParameter("RandomString");
        req.setAttribute("money",money);
        req.setAttribute("RandomString",RandomString);
        RequestDispatcher dis = req.getRequestDispatcher("depositmoney");
        dis.forward(req,res);
    }
}