import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import dao.OracleConnectionManager;
import dao.AbstractDaoFactory;
import dao.FriendDao;
import bean.UserBean;
import bean.FriendBean;

public class FriendSearchServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("windows-31j");
        String search_id = req.getParameter("id");
        HttpSession session = req.getSession();
        UserBean ub =(UserBean)session.getAttribute("ub");
        if(search_id.equals(ub.getSearch_id())){
            System.out.println("�������g��I�񂾂���");
            System.out.println("if���̒��ɓ���܂���");
            res.sendRedirect("NewFriendListServlet");
        }else{
            FriendBean fbn = new FriendBean();
            fbn.setUser_id(ub.getUser_id());
            fbn.setSearch_id(search_id);
            OracleConnectionManager.getInstance().beginTransaction();
            AbstractDaoFactory factory = AbstractDaoFactory.getFactory();
            FriendDao dao = factory.getOraFriendDao();

            FriendBean fb =dao.getSearchFriend(fbn);
            OracleConnectionManager.getInstance().commit();
            OracleConnectionManager.getInstance().closeConnection();

            req.setAttribute("fb",fb);
            RequestDispatcher dis = req.getRequestDispatcher("/NewFriendListServlet");
            dis.forward(req,res);
        }
    }
}