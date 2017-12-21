package lzj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.http.HttpFilter;

import lzj.tools.UserTools;

/**
 * Servlet Filter implementation class Normal
 */
@WebFilter(filterName = "/Normal", urlPatterns = "/*")
public class Normal extends HttpFilter implements Filter {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpFilter#HttpFilter()
	 */
	public Normal() {
		super();
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		((HttpServletRequest) request).setCharacterEncoding("UTF-8");
		((HttpServletResponse) response).setCharacterEncoding("UTF-8");
		((HttpServletResponse) response).setContentType("text/html;charset=UTF-8");
		if (((HttpServletRequest) request).getServletPath().indexOf("/login.jsp") > -1) {
			chain.doFilter(request, response);
			return;
		}
		if (((HttpServletRequest) request).getServletPath().indexOf("/signup.jsp") > -1) {
			chain.doFilter(request, response);
			return;
		}
		if (((HttpServletRequest) request).getServletPath().indexOf("/ActionServlet") > -1) {
			chain.doFilter(request, response);
			return;
		}
		if (((HttpServletRequest) request).getServletPath().indexOf("/css") > -1) {
			chain.doFilter(request, response);
			return;
		}
		if (((HttpServletRequest) request).getServletPath().indexOf("/js") > -1) {
			chain.doFilter(request, response);
			return;
		}
		if (((HttpServletRequest) request).getServletPath().indexOf("/ListenService") > -1) {
			chain.doFilter(request, response);
			return;
		}
		if (((HttpServletRequest) request).getServletPath().indexOf("/SendInfoServlet") > -1) {
			chain.doFilter(request, response);
			return;
		}
		if ((((HttpServletRequest) request).getSession().getAttribute("userObj")) == null) {
			((HttpServletRequest) request).getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			UserTools.flashUser((HttpServletRequest) request);
			chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
