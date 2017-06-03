package lzj.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lzj.entity.User;
import lzj.tools.UserTools;

/**
 * Servlet Filter implementation class Normal
 */
@WebFilter(filterName = "/Normal",urlPatterns = "/*")
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
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

		if (((User) ((HttpServletRequest) request).getSession().getAttribute("userObj")) == null) {
			((HttpServletResponse) response).sendRedirect("login.jsp");
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
	}

}
