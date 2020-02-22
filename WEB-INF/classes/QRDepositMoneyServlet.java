import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import function.CreateQRCode;
import function.PathHolder;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.PropertyDao;
import bean.UserBean;

public class QRDepositMoneyServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        PathHolder.pathName = getServletContext().getRealPath("/");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String money = req.getParameter("money");
        CreateQRCode value = CreateQRCode.getQRCode("DepositMoneyServlet?money="+money+"&RandomString=");
        ServletContext  sc = getServletContext();
        System.out.println("value.randomString"+value.randomString);
        sc.setAttribute(value.randomString,ub.getUser_id());
        req.setAttribute("title","“ü‹àQR");
        req.setAttribute("picURI",value.filePathImage);
        RequestDispatcher dis = req.getRequestDispatcher("deposit-qr");
        dis.forward(req,res);
    }
}