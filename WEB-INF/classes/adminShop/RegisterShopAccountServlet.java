package adminShop;

import java.io.IOException;

import java.util.ArrayList;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;

import function.ImageName;
import function.RandomString;
import function.EscapeString;
import function.PathHolder;
import adminShop.bean.ShopAdminUserBean;
import adminShop.dao.ShopAdminAbstractDaoFactory;
import adminShop.dao.ShopAdminUserDao;
import adminShop.bean.PropertyBean;

@WebServlet("/shopAdmin/RegisterShopAccountServlet")
@MultipartConfig(maxFileSize=1048571121)
public class RegisterShopAccountServlet extends HttpServlet{
    public void doPost(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException{
        req.setCharacterEncoding("Windows-31J");
        String name = EscapeString.escape(req.getParameter("name"));
        String mail = EscapeString.escape(req.getParameter("mail"));
        String pass = EscapeString.escape(req.getParameter("pass"));
        Part shop_image = req.getPart("shop_image");
        String realPath = PathHolder.pathName;
        String file_name = ImageName.getImageName(shop_image);
        int index = file_name.indexOf(".");
        String extension = file_name.substring(index);
        String imagePath = realPath + "/WEB-INF/shopImage/"+ RandomString.getString() + extension;
        shop_image.write(imagePath);
        ShopAdminUserBean saub = new ShopAdminUserBean();
        saub.setUser_name(name);
        saub.setMail(mail);
        saub.setPassword(pass);
        saub.setPicture(imagePath);
        ShopAdminAbstractDaoFactory factory = ShopAdminAbstractDaoFactory.getFactory();
        ShopAdminUserDao dao = factory.getShopAdminUserDao();
        dao.provisionalRegisterShopAdminUser(saub);
        req.setAttribute("message","ämîFÇ∑ÇÈÇ‹Ç≈è≠ÅXÇ®ë“ÇøÇ≠ÇæÇ≥Ç¢");
        RequestDispatcher dis = req.getRequestDispatcher("/WEB-INF/jsp/adminShop/confirm.jsp");
        dis.forward(req,res);
    }
}