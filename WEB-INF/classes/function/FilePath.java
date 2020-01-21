package function;

import javax.servlet.ServletContext;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import java.util.Set;
import java.util.Iterator;

public class FilePath{
    public static String getPath(HttpServletRequest req){
//        ServletContext context = req.getServletContext();
//        final Set<String> resourcePaths = context.getResourcePaths("/WEB-INF");
//        Iterator<String> iterator = resourcePaths.iterator();
//        String path = iterator.next();
//        System.out.println(path);

        ServletConfig servletconfig = getServletConfig();
        ServletContext servletcontext = servletconfig.getServletContext();
        String resourcePaths = servletcontext.getResourcePaths("/WEB-INF").toString();
        System.out.println("resourcePaths="+resourcePaths);
        return resourcePaths;
    }
}