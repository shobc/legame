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
import dao.ChatDao;
import bean.UserBean;
import bean.TalkBean;
import bean.ChatBean;

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
        //Ž©•ª‚ÌID
        tb.setChat_id(chat_id);

        tb.setUser_id(ub.getUser_id());

        tb.setContent(content);

        ChatBean cb = new ChatBean();

        cb.setChat_id(chat_id);

        cb.setUser_id(ub.getUser_id());

        OracleConnectionManager.getInstance().beginTransaction();
        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TalkDao dao = factory.getOraTalkDao();
        ChatDao Cdao = factory.getOraChatDao();

        if(Cdao.getJudge(cb)){
            Cdao.addChat(cb);
        }

        if(Cdao.deleteJudge(cb)){
            Cdao.updateDeleteFlag(cb);
        }


        //now
        cb.setFriend_id(ub.getUser_id());
        if(Cdao.blockJudge(cb)){
            if(Cdao.deleteJudge(cb)){
                Cdao.updateDeleteFlag(cb);
            }
        }
        String block_flag ="0";
        if(dao.blockJudge(chat_id)){
            block_flag = "1";
        }
        tb.setBlock_flag(block_flag);
        String id = dao.addTalk(tb);





        for (Part part : req.getParts()){
            String file_name = ImageName.getImageName(part);
            if (file_name != null) {
                int index = file_name.indexOf(".");
                String extension = file_name.substring(index);
                String imagePath = realPath + "/"+ RandomString.getString() + extension;
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
