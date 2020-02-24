import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
import javax.servlet.annotation.WebServlet;

import function.NewsPickup;

@WebServlet("/AccessAppServlet")
public class AccessAppServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String number = req.getParameter("number");
        ArrayList newsList = new ArrayList();
        String title = null;
        if(number==null){
            newsList = NewsPickup.getNews("1");
            title = NewsPickup.getTitle("1");
        }else{
            newsList = NewsPickup.getNews(number);
            title = NewsPickup.getTitle(number);
        }
        req.setAttribute("title",title);
        req.setAttribute("newsList",newsList);
        RequestDispatcher dis = req.getRequestDispatcher("app");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
