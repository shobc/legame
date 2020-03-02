import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AbstractDaoFactory;
import dao.FriendDao;
import bean.FriendBean;

@WebServlet("/FriendQRAddServlet")
public class FriendQRAddServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String QRCode = req.getParameter("QRCode");
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        FriendDao dao = factory.getOraFriendDao();
        FriendBean fb = dao.getFriendQRUser_id(QRCode);
        req.setAttribute("fb",fb);
        RequestDispatcher dis = req.getRequestDispatcher("no-friend-profile");
        dis.forward(req,res);
    }
}