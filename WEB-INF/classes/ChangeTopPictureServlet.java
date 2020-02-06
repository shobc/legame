import java.io.IOException;

import java.io.File;
import org.apache.commons.io.FileUtils;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.ProfileDao;
import bean.UserBean;

import function.ImageName;
import function.RandomString;

@MultipartConfig(maxFileSize=1048571121)
public class ChangeTopPictureServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String realPath =  getServletContext().getRealPath("/WEB-INF/image");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();
        for (Part part : req.getParts()){
            String file_name = ImageName.getImageName(part);
            if (file_name != null) {
                int index = file_name.indexOf(".");
                String extension = file_name.substring(index);
                ub.setTop_picture(realPath + "/"+ RandomString.getString() + extension);
                part.write(ub.getTop_picture());
                dao.UpdateUserTopPicture(ub);
            }
        }
        byte[]fileContent = FileUtils.readFileToByteArray(new File(ub.getTop_picture()));
        ub.setTop_picture(Base64.getEncoder().encodeToString(fileContent));
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        res.sendRedirect("profilesetting");
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
