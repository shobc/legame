package adminShop;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import function.EscapeString;
import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminUserDao;
import adminShop.bean.ShopAdminUserBean;
import adminShop.exception.RegisterAccountException;
import function.SendMail;
import function.RandomString;

@WebServlet("/shopAdmin/PasswordReminderServlet")
public class PasswordReminderServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        ServletContext  sc = getServletContext();
        String mail = EscapeString.escape(req.getParameter("mail"));
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        if(dao.emailJudge(mail)){
            throw new RegisterAccountException("メールアドレスが登録されていません");
        }
        String RString = RandomString.getString();
        ShopAdminUserBean saub = new ShopAdminUserBean();
        saub.setMail(mail);
        saub.setRandom(RString);
        String url = "http://localhost:8080/legame/shopAdmin/AuthAccountServlet?RandomCode="+RString;
        SendMail sm = new SendMail();
        String message = "URLにアクセスしてパスワード変更してください";
        sm.send(mail,message,url);
        sc.setAttribute(RString,saub);
        req.setAttribute("message","メールを送信しました");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/confirm.jsp");
        dis.forward(req,res);
    }
}