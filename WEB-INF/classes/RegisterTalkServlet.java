import java.io.IOException;
import java.util.ArrayList;

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
import dao.TalkDao;
import bean.UserBean;
import bean.TalkBean;

import function.ImageName;
import function.RandomString;

@MultipartConfig(maxFileSize=1048571121)
public class RegisterTalkServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String realPath =  getServletContext().getRealPath("/WEB-INF/image");
        String chat_id = req.getParameter("chat_id");
        String content = req.getParameter("content");
        HttpSession session = req.getSession();
        UserBean ub = (UserBean)session.getAttribute("ub");
        TalkBean tb = new TalkBean();
        tb.setChat_id(chat_id);
        tb.setUser_id(ub.getUser_id());
        tb.setContent(content);
        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TalkDao dao = factory.getOraTalkDao();

        String id = dao.addTalk(tb);
        for (Part part : req.getParts()){
            String file_name = ImageName.getImageName(part);
            System.out.println("file_name="+file_name);
            if (file_name != null) {
                int index = file_name.indexOf(".");
                String extension = file_name.substring(index);
                String imagePath = realPath + "/"+ RandomString.getString() + extension;
                System.out.println("imagePath="+imagePath);
                part.write(imagePath);
                dao.addTalkPicture(id,imagePath);
            }
        }

        OracleConnectionManager.getInstance().commit();
        OracleConnectionManager.getInstance().closeConnection();

        res.sendRedirect("TalkPageServlet?chat_id="+chat_id);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
