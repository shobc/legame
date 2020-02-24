import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.ProfileDao;
import bean.UserBean;
import function.EscapeString;

@WebServlet("/ChangeSingleWordServlet")
public class ChangeSingleWordServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String single_word = EscapeString.escape(req.getParameter("single_word"));
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        ub.setSingle_word(single_word);
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();
        dao.updateSingleWordProfile(ub);
        res.sendRedirect("profile-setting");
    }
}