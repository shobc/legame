package listener;

import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContext;

import function.PathHolder;

public class LoadListener
        implements ServletContextListener {
    public void contextInitialized(ServletContextEvent scae) {
        ServletContext sc = scae.getServletContext();
        PathHolder.pathName = sc.getRealPath("/");
    }
    public void contextDestroyed(ServletContextEvent scae) {}
}
