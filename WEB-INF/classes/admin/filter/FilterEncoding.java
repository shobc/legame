package admin.filter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class FilterEncoding implements Filter {
    private FilterConfig config=null;
    public void init(FilterConfig conf){
        this.config=conf;
    }
    public void destroy(){}
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        request.setCharacterEncoding(config.getInitParameter("encoding"));
        chain.doFilter(request,response);
    }
}