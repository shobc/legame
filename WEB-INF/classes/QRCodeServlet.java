import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import function.CreateQRCode;
import function.PathHolder;
import DB.UpdateQRCodeDB;
import bean.UserBean;

public class QRCodeServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        PathHolder.pathName = getServletContext().getRealPath("/");
        UserBean ub = (UserBean)session.getAttribute("ub");
        CreateQRCode value = CreateQRCode.getQRCode(ub.getUser_id());
        UpdateQRCodeDB.updateQRCode(ub.getUser_id(),value.randomString);
        req.setAttribute("picURI",ub.getUser_id()+value.filePathImage);
        RequestDispatcher dis = req.getRequestDispatcher("qrcode");
        dis.forward(req,res);
    }
}