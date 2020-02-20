import java.io.IOException;
//import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import function.BinaryToBufferedImage;
import function.Base64Decode;

import function.ProfileImageName;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.ProfileDao;
import function.PathHolder;
import bean.UserBean;

public class RegisterProfileServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String user_id = req.getParameter("user_id");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String comment = req.getParameter("comment");
        String base64Image = req.getParameter("base64Image");

        Base64Decode bd = new Base64Decode();
        bd.setImagePath(base64Image);
        UserBean ub = new UserBean();
        ub.setUser_id(user_id);
        ub.setSearch_id(id);
        ub.setSingle_word(comment);
        ub.setName(name);
        ub.setTop_picture(bd.getFilePath("WEB-INF/image/"));

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();

        dao.addProfile(ub);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        RequestDispatcher dis = req.getRequestDispatcher("registered-profile");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}