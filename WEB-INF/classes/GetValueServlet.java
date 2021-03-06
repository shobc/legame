import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpSession;

import bean.LoginUserBean;
import exception.NoMatchRandomException;

@WebServlet("/GetValueServlet")
public class GetValueServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String RandomCode = req.getParameter("RandomCode");
        ServletContext sc = getServletContext();
        LoginUserBean lub = (LoginUserBean)sc.getAttribute(RandomCode);
        if(RandomCode.equals("")||lub==null){
            throw new NoMatchRandomException("該当しない文字列を受け付けました");
        }
        String random = lub.getRandomCode();
        if(!RandomCode.equals(random)){
            throw new NoMatchRandomException("該当しない文字列を受け付けました");
        }
        RequestDispatcher dis = req.getRequestDispatcher("password-reissue");
        dis.forward(req,res);
    }
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        doPost(req, res);
    }
}
