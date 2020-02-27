import java.io.IOException;
//import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.AbstractDaoFactory;
import dao.ProfileDao;
import bean.UserBean;
import function.PathHolder;
import function.Base64Decode;
import function.EscapeString;
import exception.SufferNewRegisterSearchIdException;

import javax.servlet.annotation.WebServlet;
@WebServlet("/RegisterProfileServlet")
public class RegisterProfileServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String user_id = req.getParameter("user_id");
        String name = EscapeString.escape(req.getParameter("name"));
        String id = EscapeString.escape(req.getParameter("id"));
        String comment = EscapeString.escape(req.getParameter("comment"));
        String base64Image = req.getParameter("base64Image");
        UserBean ub = new UserBean();
        ub.setUser_id(user_id);
        ub.setSearch_id(id);
        ub.setSingle_word(comment);
        ub.setName(name);
        if(!base64Image.equals("")){
            Base64Decode bd = new Base64Decode();
            bd.setImagePath(base64Image);
            ub.setTop_picture(bd.getFilePath("WEB-INF/image/"));
        }else{
            ub.setTop_picture(PathHolder.pathName+"image/noimage.png");
        }

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        ProfileDao dao = factory.getOraProfileDao();
        if(dao.sufferSearchId(id)){
            throw new SufferNewRegisterSearchIdException("ÇªÇÃIDÇÕégópÇ≈Ç´Ç‹ÇπÇÒ");
        }
        dao.addProfile(ub);

        req.setAttribute("message","ìoò^Ç≥ÇÍÇ‹ÇµÇΩÅI");
        RequestDispatcher dis = req.getRequestDispatcher("confirm");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doPost(req,res);
    }
}