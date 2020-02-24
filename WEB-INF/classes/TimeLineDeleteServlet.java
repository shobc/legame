import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.TimeLineDao;

@WebServlet("/TimeLineDeleteServlet")
public class TimeLineDeleteServlet extends HttpServlet{
    public void doGet(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        String timeline_id = req.getParameter("timeline_id");

        AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
        TimeLineDao dao = factory.getOraTimeLineDao();

        dao.deleteTimeLine(timeline_id);

        res.sendRedirect("TimeLineServlet");
    }
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        this.doGet(req,res);
    }
}