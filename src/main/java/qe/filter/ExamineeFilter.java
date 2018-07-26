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

@WebFilter(filterName = "ExamineeFilter",urlPatterns = "/exam/*")
public class ExamineeFilter implements Filter{

    private static Log log = LogFactory.getLog(ExamineeFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("Exmainee Filter initialized");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        String type = (String)session.getAttribute("type");
        if(type!=null){
            if(type.equals("common")){
                return;
            }
        }
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        response.sendRedirect("/index.html");
        response.getWriter().print("<script language=\"javascript\">alert('请先登陆');window.location.href='/index.html'</script>");
        log.info("ip: "+request.getRemoteAddr()+" tend to use admin page without login");
    }

    @Override
    public void destroy() {
        log.info("Emainee Filter destroyed");
    }
}
