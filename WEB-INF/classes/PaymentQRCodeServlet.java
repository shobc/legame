import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import function.CreateQRCode;

import dao.AbstractDaoFactory;
import dao.PropertyDao;
import bean.UserBean;

@WebServlet("/PaymentQRCodeServlet")
public class PaymentQRCodeServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        CreateQRCode value = CreateQRCode.getQRCode("shopAdmin/PayServlet?RandomString=");

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        PropertyDao dao = factory.getOraPropertyDao();

        dao.updateQRCode(user_id,value.randomString);

        req.setAttribute("picURI",value.filePathImage);
        req.setAttribute("title","QRåàçœ");
        RequestDispatcher dis = req.getRequestDispatcher("payment");
        dis.forward(req,res);
    }
}