import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

//session��؂�p��servlet
@WebServlet("/DeleteSessionServlet")
public class DeleteSessionServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        //session�̎擾
        HttpSession session = req.getSession();
        //session��؂�
        session.invalidate();
        //session��؂������Ƃɍs���y�[�W
        res.sendRedirect("login");
    }
}