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
import function.WeatherPickup;
import bean.WeatherBean;

@WebServlet("/AccessAppServlet")
public class AccessAppServlet extends HttpServlet{
    String number = "1";
    String weather_num = "1";
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        if(req.getParameter("number")!=null){
            number = req.getParameter("number");
        }
        if(req.getParameter("weather_num")!=null){
            weather_num = req.getParameter("weather_num");
        }
        ArrayList newsList = new ArrayList();
        newsList = NewsPickup.getNews(number);
        String title = NewsPickup.getTitle(number);

        WeatherBean wb = new WeatherBean();
        wb = WeatherPickup.getWeather(weather_num);
        String location = WeatherPickup.getLocation(weather_num);

        req.setAttribute("title",title);
        req.setAttribute("newsList",newsList);
        req.setAttribute("number",number);
        req.setAttribute("weather_num",weather_num);
        req.setAttribute("location",location);
        req.setAttribute("wb",wb);
        RequestDispatcher dis = req.getRequestDispatcher("app");
        dis.forward(req,res);
    }
    public void doGet (HttpServletRequest req, HttpServletResponse res)throws IOException, ServletException {
        this.doPost(req,res);
    }
}
