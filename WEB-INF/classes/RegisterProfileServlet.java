import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import function.ProfileImageName;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.ProfileDao;
import function.PathHolder;
import bean.UserBean;

@MultipartConfig(maxFileSize=1048571121)
public class RegisterProfileServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String user_id = req.getParameter("user_id");
        String name = req.getParameter("name");
        String id = req.getParameter("id");
        String comment = req.getParameter("comment");
        Part part = req.getPart("profileImage");
        String realPath = PathHolder.pathName;
        String file_name = ProfileImageName.getProfileImageName(part);
        String pathPic = null;
        if(file_name.equals("")){
            pathPic = realPath+ "WEB-INF/image/noimage.jpg";
        }else{
            pathPic = realPath+ "WEB-INF/image/" + file_name;
            part.write(pathPic);
        }
        UserBean ub = new UserBean();
        ub.setUser_id(user_id);
        ub.setSearch_id(id);
        ub.setSingle_word(comment);
        ub.setName(name);
        ub.setTop_picture(pathPic);

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();

        dao.addProfile(ub);

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        RequestDispatcher dis = req.getRequestDispatcher("registeredProfilePage");
        dis.forward(req,res);
    }
}