package function;

import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.GenericServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServlet;
import java.util.Set;
import java.util.Iterator;

public class FilePath extends HttpServlet{
//    public static String getPath(HttpServletRequest req){
    public void getPath(HttpServletRequest req){
//        ServletContext context = req.getServletContext();
//        final Set<String> resourcePaths = context.getResourcePaths("/WEB-INF");
//        Iterator<String> iterator = resourcePaths.iterator();
//        String path = iterator.next();
//        System.out.println(path);

        ServletConfig servletconfig = GenericServlet.getServletConfig();
        ServletContext servletcontext = servletconfig.getServletContext();
        String resourcePaths = servletcontext.getResourcePaths("/WEB-INF").toString();
        System.out.println("resourcePaths="+resourcePaths);
    }
}