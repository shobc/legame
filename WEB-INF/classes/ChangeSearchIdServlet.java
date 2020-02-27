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
import exception.SufferSearchIdException;

@WebServlet("/ChangeSearchIdServlet")
public class ChangeSearchIdServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String search_id =EscapeString.escape(req.getParameter("search_id"));
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        if(search_id.equals(ub.getSearch_id())) {
            throw new SufferSearchIdException("�O�Ɠ���ID�ɂȂ�܂�");
        }
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();
        if(dao.sufferSearchId(search_id)){
            throw new SufferSearchIdException("����ID�͎g�p�ł��܂���");
        }
        ub.setSearch_id(search_id);
        dao.updateSearchIdProfile(ub);
        res.sendRedirect("profile-setting");
    }
}