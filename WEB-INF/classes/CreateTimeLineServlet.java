import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import bean.UserBean;
import bean.TimeLineBean;
import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;
import function.ImageName;
import function.RandomString;
import function.EscapeString;
import function.PathHolder;

@WebServlet("/CreateTimeLineServlet")
@MultipartConfig(maxFileSize=1048571121)
public class CreateTimeLineServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        String realPath =  PathHolder.pathName;
        String timeline_sentence = EscapeString.escape(req.getParameter("timeline_sentence"));
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        String user_id = ub.getUser_id();
        TimeLineBean tlb = new TimeLineBean();
        tlb.setUser_id(user_id);
        tlb.setTimeline_sentence(timeline_sentence);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getOraTimeLineDao();
        String id = dao.addTimeline(tlb);
        for (Part part : req.getParts()) {
            String file_name = ImageName.getImageName(part);
            if (file_name != null&&!file_name.equals("")) {
                int index = file_name.indexOf(".");
                String extension = file_name.substring(index);
                String imagePath = realPath + "/WEB-INF/image/"+ RandomString.getString() + extension;
                part.write(imagePath);
                dao.addTimelinePicture(id,imagePath);
            }
        }
        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        res.sendRedirect("TimeLineServlet");
    }
}