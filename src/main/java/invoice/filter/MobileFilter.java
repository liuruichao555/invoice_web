package invoice.filter;

import invoice.utils.CheckMobile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * MobileFilter
 *
 * @author liuruichao
 * Created on 2017/1/11 15:12
 */
@WebFilter(filterName = "mobileFilter", urlPatterns = "/*")
public class MobileFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String userAgent = request.getHeader( "USER-AGENT" ).toLowerCase();
        String uri = request.getRequestURI();
        boolean isFromMobile = CheckMobile.check(userAgent);
        if (isFromMobile
                && !uri.startsWith("/mobile")
                && !uri.startsWith("/css")
                && !uri.startsWith("/js")
                && !uri.startsWith("/images")
                && !uri.startsWith("/rest")) {
            response.sendRedirect("/mobile" + uri);
            return;
        } else if (!isFromMobile && uri.startsWith("/mobile")) {
            response.sendRedirect(uri.replace("/mobile", ""));
            return;
        }

        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
