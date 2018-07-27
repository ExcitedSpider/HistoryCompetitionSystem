package qe.filter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AdminAuthFilter", urlPatterns = "/admin/*")
public class AdminAuthFilter implements Filter {

    private static Log log = LogFactory.getLog(AdminAuthFilter.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Admin Auth Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String type = (String) session.getAttribute("type");
        if(type!=null){
            if(type.equals("admin")){
                filterChain.doFilter(servletRequest,servletResponse);
                return;
            }
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.getWriter().print("<script language=\"javascript\">alert('请先登陆');window.location.href='/index.html'</script>");
        response.sendRedirect("/index.html");
        log.info("ip: "+request.getRemoteAddr()+" tend to use admin page without login");
    }

    @Override
    public void destroy() {
        log.info("Admin Auth Filter destoryed");
    }
}
