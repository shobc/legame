import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//session��؂�p��servlet
public class DSession extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        //session�̎擾
        HttpSession session = req.getSession();
        //session��؂�
        session.invalidate();
        System.out.println("session��؂�܂���");
        //session��؂������Ƃɍs���y�[�W
        res.sendRedirect("login");
    }
}