import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.ChatDao;
import bean.UserBean;

@WebServlet("/StopAccountDeleteSessionServlet")
public class StopAccountDeleteSessionServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        HttpSession session = req.getSession();
        session.invalidate();
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/error/no-login.jsp");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
