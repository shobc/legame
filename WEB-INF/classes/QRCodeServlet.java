import java.io.IOException;

import javax.servlet.ServletException;
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

public class QRCodeServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        PathHolder.pathName = getServletContext().getRealPath("/");
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        CreateQRCode value = CreateQRCode.getQRCode("PayServlet?RandomString=");

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        PropertyDao dao = factory.getOraPropertyDao();

        dao.updateQRCode(user_id,value.randomString);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        req.setAttribute("picURI",value.filePathImage);
        RequestDispatcher dis = req.getRequestDispatcher("qrcode");
        dis.forward(req,res);
    }
}