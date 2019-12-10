import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//sessionを切る用のservlet
public class DSession extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        //sessionの取得
        HttpSession session = req.getSession();
        //sessionを切る
        session.invalidate();
        System.out.println("sessionを切りました");
        //sessionを切ったあとに行くページ
        res.sendRedirect("loginPage");
    }
}