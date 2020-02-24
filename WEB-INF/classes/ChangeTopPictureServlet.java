import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import dao.AbstractDaoFactory;
import dao.ProfileDao;
import bean.UserBean;

import function.Base64Decode;

@WebServlet("/ChangeTopPictureServlet")
public class ChangeTopPictureServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String base64Image = req.getParameter("base64Image");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();
        Base64Decode bd = new Base64Decode();
        bd.setImagePath(base64Image);
        ub.setTop_picture(bd.getFilePath("WEB-INF/image/"));
        dao.UpdateUserTopPicture(ub);
        ub.setTop_picture(bd.getBase64());
        res.sendRedirect("profile-setting");
    }
}
